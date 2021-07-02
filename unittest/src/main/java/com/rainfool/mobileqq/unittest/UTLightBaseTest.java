package com.rainfool.mobileqq.unittest;

import org.junit.QQUnitTestRunner;
import org.junit.runner.RunWith;

/**
 * 手Q轻量单测专用基类
 * 指定QQUnitTestRunner运行器，不包含Robolectric环境，但是包含手Q运行时环境，适合不依赖android结果的单测
 */
@RunWith(QQUnitTestRunner.class)
public abstract class UTLightBaseTest {
}
