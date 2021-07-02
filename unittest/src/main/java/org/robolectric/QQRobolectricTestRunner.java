package org.robolectric;

import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;
import org.robolectric.internal.bytecode.Sandbox;

public class QQRobolectricTestRunner extends RobolectricTestRunner {

    static {
        MavenRoboSettings.setMavenRepositoryUrl("https://mirrors.tencent.com/nexus/repository/maven-public/");
    }

    public QQRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected void beforeTest(Sandbox sandbox, FrameworkMethod method, Method bootstrappedMethod) throws Throwable {
        super.beforeTest(sandbox, method, bootstrappedMethod);
    }

    @Override
    protected void afterTest(FrameworkMethod method, Method bootstrappedMethod) {
        super.afterTest(method, bootstrappedMethod);
    }

    // 兼容 JMockit
    @Nonnull
    @Override
    protected InstrumentationConfiguration createClassLoaderConfig(FrameworkMethod method) {
        System.setProperty("org.robolectric.packagesToNotAcquire", "mockit");
        return super.createClassLoaderConfig(method);
    }

}
