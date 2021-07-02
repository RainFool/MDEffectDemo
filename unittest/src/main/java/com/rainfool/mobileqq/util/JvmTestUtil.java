package com.rainfool.mobileqq.util;

public class JvmTestUtil {

    /**
     * 日志打印
     */
    public static void printLog(String log) {
        System.out.println(System.currentTimeMillis() + "|" + Thread.currentThread().getName() + "[" + Thread.currentThread().getId() + "]" + log);
    }

}
