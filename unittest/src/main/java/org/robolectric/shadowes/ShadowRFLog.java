package org.robolectric.shadowes;


import com.tencent.biz.richframework.delegate.impl.RFLog;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * 描述
 *
 * @author lahmlong
 * @date 2021/4/29
 * 提供一个RFLog的shadow类，避免真实执行
 * 调用方式可参考
 * @Config(shadows = {ShadowRFLog.class})
 */
@Implements(RFLog.class)
public class ShadowRFLog {
    @Implementation
    public static void i(String tag, int reportLevel, String msg) {

    }

    @Implementation
    public static void w(String tag, int reportLevel, String msg) {

    }

    @Implementation
    public static void d(String tag, int reportLevel, String msg) {

    }

    @Implementation
    public static void d(String tag, int reportLevel, Object... msg) {

    }

    @Implementation
    public static void e(String tag, int reportLevel, String msg) {

    }

    @Implementation
    public static void e(String tag, int reportLevel, Object... msg) {

    }

    @Implementation
    public static boolean isColorLevel() {
        return false;
    }

    @Implementation
    public static boolean isDevelopLevel() {
        return false;
    }
}
