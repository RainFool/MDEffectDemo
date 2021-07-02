package org.robolectric.checker;

import com.tencent.mobileqq.app.ThreadManagerV2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.runners.model.FrameworkMethod;
import org.robolectric.internal.bytecode.Sandbox;

/**
 * Create by Jairochen on 2020/8/25
 * Description :ThreadManagerV2.SUB_THREAD_HANDLER 检查器
 */
public class ThreadManagerV2Checker implements IChecker {

    @Override
    public boolean doCheckBeforeTest(Sandbox sandbox, ClassLoader classLoader,
                                     FrameworkMethod method, Method bootstrappedMethod) {
        return true;
    }

    @Override
    public boolean doCheckAfterTest(ClassLoader classLoader, FrameworkMethod method) {
        try {
            Class clazz = classLoader.loadClass(ThreadManagerV2.class.getName());
            Field field = clazz.getDeclaredField("SUB_THREAD_HANDLER");
            field.setAccessible(true);
            Object object = field.get(null);
            return !(object != null && object.toString().contains("Mock"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean canIRepair(ClassLoader classLoader, FrameworkMethod method) {
        try {
            Class clazz = classLoader.loadClass(ThreadManagerV2.class.getName());
            Field field = clazz.getDeclaredField("SUB_THREAD_HANDLER");
            field.setAccessible(true);
            field.set(null, null);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getSuggest() {
        return "PLZ recover static field after test!";
    }
}
