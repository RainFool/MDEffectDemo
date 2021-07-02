package org.junit;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import com.tencent.common.config.AppSetting;
import com.rainfool.mobileqq.util.ReflectUtil;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @author: jensenweng
 * @data: 2021/5/20
 * @Desp: QQ单测运行器，没有Robolectric环境，适合不需要依赖android结果的场景
 */
public class QQUnitTestRunner extends BlockJUnit4ClassRunner {
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @throws InitializationError if the test class is malformed.
     */
    public QQUnitTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        initEnvironment();
        createApplication();
    }

    private void createApplication() {
        try {
            long startTime = System.currentTimeMillis();
            Class<?> clz = Class.forName(UTQFixApplication.DELEGATE_CLASS);
            Application application = (Application) clz.newInstance();
            ReflectUtil.invokeMethod(application, "attachBaseContext", new Class[]{Context.class}, new Object[]{null});
            application.onCreate();
            System.out.println("****** QQUnitTestRunner createApplication cost:" + (System.currentTimeMillis() - startTime) + " ******");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEnvironment() {
        ReflectUtil.setStaticFieldValue(AppSetting.class, "isSetArgsFromManifest", true);
        if (Looper.myLooper() == null) {
            Looper.prepareMainLooper();
        }
    }
}
