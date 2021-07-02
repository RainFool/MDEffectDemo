package org.robolectric.shadowes;

import com.tencent.qphone.base.util.QLog;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * @author lingtaozeng
 * @date 2020/9/28
 * 一个QLog的shadow类，可以在减少QLog内部逻辑执行
 */
@Implements(QLog.class)
public class ShadowQLog {

    @Implementation
    public static void e(String tag, int reportLevel, String msgString, Throwable tr) {
    }

    @Implementation
    public static void e(String tag, int reportLevel, byte[] msgBytes, Throwable tr) {
    }

    @Implementation
    public static boolean isColorLevel() {
        return true;
    }

    @Implementation
    public static void w(String tag, int reportLevel, String msgString, Throwable tr) {
    }

    @Implementation
    public static void w(String tag, int reportLevel, byte[] msgBytes, Throwable tr) {
    }

    @Implementation
    public static void i(String tag, int reportLevel, String msgString, Throwable tr) {
    }

    @Implementation
    public static void i(String tag, int reportLevel, byte[] msgBytes, Throwable tr) {
    }


    @Implementation
    public static void d(String tag, int reportLevel, String msg) {
    }

    @Implementation
    public static void d(String tag, int reportLevel, String msg, Throwable tr) {
    }


    @Implementation
    public static void d(String tag, int reportLevel, byte[] msgBytes, Throwable tr) {
    }

}

