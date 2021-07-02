package org.robolectric.checker;

import com.tencent.mobileqq.util.JvmTestUtil;
import java.lang.reflect.Method;
import org.junit.runners.model.FrameworkMethod;
import org.robolectric.internal.bytecode.Sandbox;

/**
 * Create by Jairochen on 2020/8/25
 * Description : 内存检查
 */
public class MemoryChecker implements IChecker {

    @Override
    public boolean doCheckBeforeTest(Sandbox sandbox, ClassLoader classLoader, FrameworkMethod method, Method bootstrappedMethod) {
        printRuntimeMem("beforeTest");
        return true;
    }

    @Override
    public boolean doCheckAfterTest(ClassLoader classLoader, FrameworkMethod method) {
        printRuntimeMem("afterTest");
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

    private void printRuntimeMem(String scene) {
        long maxHeap = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long currentHeap = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long freeHeap = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        JvmTestUtil.printLog(scene + "|maxHeap = " + maxHeap + " MB, currentHeap = " + currentHeap + " MB, freeHeap = " + freeHeap + " MB");
    }
}
