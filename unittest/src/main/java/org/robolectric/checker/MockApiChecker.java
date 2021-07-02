package org.robolectric.checker;

import com.rainfool.mobileqq.util.JvmTestUtil;
import java.lang.reflect.Method;
import org.junit.runners.model.FrameworkMethod;
import org.robolectric.annotation.MockApi;
import org.robolectric.internal.bytecode.Sandbox;
import org.robolectric.shadowes.ShadowQRoute;
import org.robolectric.util.ReflectionHelpers;
import org.robolectric.util.ReflectionHelpers.ClassParameter;

/**
 * 需要mock的QRouteApi接口自动添加器
 * 将用例声明的注解{@link MockApi} 中的API接口
 * 添加到{@link ShadowQRoute# mockClassMap} 中
 * 当用例执行完后自动移除
 */
public class MockApiChecker implements IChecker {

    @Override
    public boolean doCheckBeforeTest(Sandbox sandbox, ClassLoader classLoader, FrameworkMethod method, Method bootstrappedMethod) {
        try {
            // 用例执行时的ClassLoader不一样，此处要用sandbox的classLoader
            Class clazz = classLoader.loadClass(ShadowQRoute.class.getName());
            // 读取用例声明的mock对象
            MockApi mockApi = method.getDeclaringClass().getAnnotation(MockApi.class);
            if (mockApi != null) {
                Class<?>[] apiClasses = mockApi.api();
                if (apiClasses != null) {
                    for (Class apiClazz : apiClasses) {
                        Class realClazz = classLoader.loadClass(apiClazz.getName());
                        ReflectionHelpers.callStaticMethod(clazz, "addMockClass",
                                ClassParameter.from(Class.class, realClazz));
                        JvmTestUtil.printLog("addMockClass api: " + realClazz);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean doCheckAfterTest(ClassLoader classLoader, FrameworkMethod method) {
        try {
            // 用例执行时的ClassLoader不一样，此处要用sandbox的classLoader
            Class clazz = classLoader.loadClass(ShadowQRoute.class.getName());
            // 读取用例声明的mock对象
            MockApi mockApi = method.getDeclaringClass().getAnnotation(MockApi.class);
            if (mockApi != null) {
                Class<?>[] apiClasses = mockApi.api();
                if (apiClasses != null) {
                    for (Class apiClazz : apiClasses) {
                        Class realClazz = classLoader.loadClass(apiClazz.getName());
                        ReflectionHelpers.callStaticMethod(clazz, "removeMockClass",
                                ClassParameter.from(Class.class, realClazz));
                        JvmTestUtil.printLog("removeMockClass api: " + realClazz);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
