package org.robolectric.checker;

import java.lang.reflect.Method;
import org.junit.runners.model.FrameworkMethod;
import org.robolectric.internal.bytecode.Sandbox;

/**
 * Create by Jairochen on 2020/8/25
 * Description : 通用检查接口
 */
public interface IChecker {

    boolean doCheckBeforeTest(Sandbox sandbox, ClassLoader classLoader, FrameworkMethod method, Method bootstrappedMethod);

    boolean doCheckAfterTest(ClassLoader classLoader, FrameworkMethod method);

    boolean canIRepair(ClassLoader classLoader, FrameworkMethod method);

    String getSuggest();
}
