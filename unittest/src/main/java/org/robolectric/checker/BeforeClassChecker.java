package org.robolectric.checker;

import com.rainfool.mobileqq.util.JvmTestUtil;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;
import org.robolectric.internal.bytecode.Sandbox;

/**
 * 首个Test类执行beforeTest前默认执行公共的BeforeClass方法，提前异步初始化Mockito
 */
public class BeforeClassChecker implements IChecker {

    private static HashSet<String> loadedSet = new HashSet<String>();

    @BeforeClass
    public static void beforeClass() {
        new Thread() {
            @Override
            public void run() {
                long st = System.currentTimeMillis();
                JvmTestUtil.printLog("beforeClass st:" + st);
                JvmTestUtil.printLog("beforeClass cost:" + (System.currentTimeMillis() - st));
            }
        }.start();
    }

    @Override
    public boolean doCheckBeforeTest(Sandbox sandbox, ClassLoader classLoader, FrameworkMethod method, Method bootstrappedMethod) {
        if (!loadedSet.contains(classLoader.toString())) {
            loadedSet.add(classLoader.toString());
            TestClass curClass = new TestClass(BeforeClassChecker.class);
            Class bootstrappedTestClass =
                    sandbox.bootstrappedClass(curClass.getJavaClass());
            final TestClass testClass = new TestClass(bootstrappedTestClass);
            final List<FrameworkMethod> befores = testClass.getAnnotatedMethods(BeforeClass.class);
            for (FrameworkMethod before : befores) {
                try {
                    before.invokeExplosively(null);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public boolean doCheckAfterTest(ClassLoader classLoader, FrameworkMethod method) {
        return true;
    }

    @Override
    public boolean canIRepair(ClassLoader classLoader, FrameworkMethod method) {
        return false;
    }

    @Override
    public String getSuggest() {
        return "";
    }
}
