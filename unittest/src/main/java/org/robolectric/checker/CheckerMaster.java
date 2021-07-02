package org.robolectric.checker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;
import org.robolectric.internal.AndroidSandbox;
import org.robolectric.internal.bytecode.Sandbox;

/**
 * Create by Jairochen on 2020/8/25
 * Description : 测试用例前后检查
 */
public class CheckerMaster {
    static List<IChecker> checkers = new ArrayList<IChecker>();
    static ArrayList<String> clazzNameList = new ArrayList<String>(); //需要反射调用的checker完整路径

    static {
        checkers.clear();
        checkers.add(new MemoryChecker()); // 内存使用情况检查 @author=maxtaohuang
//        checkers.add(new ThreadManagerV2Checker()); // 线程常量修改监控 @author=jairochen
        checkers.add(new BeforeClassChecker()); // 测试用例预加载 @author=alberthe
        checkers.add(new MockApiChecker()); // 默认mock的QRouteApi @author=alberthe
//        checkers.add(new BaseApplicationImplChecker()); // BaseApplicationImpl常量监控 @author=jairochen

        reflectGetCheckers();
    }

    //增加通过反射调用的checker
    private static void reflectGetCheckers() {
        clazzNameList.clear();
        clazzNameList.add("org.robolectric.checker.BaseApplicationImplChecker");

        for (String clazzName : clazzNameList) {
            Class<?> clz;
            Object checker;
            try {
                clz = Class.forName(clazzName);
                checker = clz.newInstance();
            } catch (Exception e) {
                clz = null;
                checker = null;
                System.out.println("reflectGetCheckers fail.clazzName:" + clazzName + e.toString());
            }
            if (checker instanceof IChecker) {
                checkers.add((IChecker) checker);
            }
        }
    }

    public static void callMasterToCheckBeforeTest(Sandbox sandbox, FrameworkMethod method, Method bootstrappedMethod) {

        for (IChecker checker : checkers) {
            boolean result = checker.doCheckBeforeTest(sandbox, sandbox.getRobolectricClassLoader(),
                    method, bootstrappedMethod);
            if (!result) {
                System.out.println("Before Test Check Fail By " + checker.getClass().getName()
                        + ", Wrong Method=" + method.getMethod().toString());
            }
        }
    }

    public static void callMasterToCheckAfterTest(AndroidSandbox sandbox,
                                                  FrameworkMethod method, Method bootstrappedMethod) {

        for (IChecker checker : checkers) {
            boolean result = checker.doCheckAfterTest(sandbox.getRobolectricClassLoader(), method);
            if (!result) {
                System.out.println("After Test Check Fail By " + checker.getClass().getName() + ", Wrong Method="
                        + method.getMethod().toString());
                boolean repairResult = checker.canIRepair(sandbox.getRobolectricClassLoader(), method);
                System.out.println("I help you repair problem in order to protect another test case normal. " +
                        "Repair Result=" + repairResult);
                Assert.fail("Please check your test case which affects another test case, the suggest is "
                        + checker.getSuggest() + " repairResult=" + repairResult);
            }
        }
    }
}
