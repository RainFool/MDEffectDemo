package org.robolectric;

import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.robolectric.checker.CheckerMaster;
import org.robolectric.internal.AndroidSandbox;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;
import org.robolectric.internal.bytecode.Sandbox;
import org.robolectric.shadowes.ShadowQLog;

public class QQRobolectricTestRunner extends RobolectricTestRunner {

    static {
        MavenRoboSettings.setMavenRepositoryUrl("https://mirrors.tencent.com/nexus/repository/maven-public/");
    }

    public QQRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected void beforeTest(Sandbox sandbox, FrameworkMethod method, Method bootstrappedMethod) throws Throwable {
        CheckerMaster.callMasterToCheckBeforeTest(sandbox, method, bootstrappedMethod);
        super.beforeTest(sandbox, method, bootstrappedMethod);
    }

    @Override
    protected void afterTest(FrameworkMethod method, Method bootstrappedMethod) {
        RobolectricFrameworkMethod roboMethod = (RobolectricFrameworkMethod) method;
        CheckerMaster.callMasterToCheckAfterTest((AndroidSandbox) roboMethod.getSandbox(), method, bootstrappedMethod);
        super.afterTest(method, bootstrappedMethod);
    }

    // 兼容 JMockit
    @Nonnull
    @Override
    protected InstrumentationConfiguration createClassLoaderConfig(FrameworkMethod method) {
        System.setProperty("org.robolectric.packagesToNotAcquire", "mockit");
        return super.createClassLoaderConfig(method);
    }

    /**
     * 在用例声明的shadow类列表中默认加上{@link ShadowQLog}
     //这里不默认加ShadowQRoute，会增加无谓耗时，有需要的类自己类里指定
     */
//    @Nonnull
//    @Override
//    protected Class<?>[] getExtraShadows(FrameworkMethod frameworkMethod) {
//        Class<?>[] originShadows = super.getExtraShadows(frameworkMethod);
//        Class<?>[] newShadows = new Class<?>[originShadows.length + 1];
//        for (int i = 0; i < originShadows.length; i++) {
//            newShadows[i] = originShadows[i];
//        }
//        newShadows[originShadows.length] = ShadowQLog.class;
//        return newShadows;
//    }
}
