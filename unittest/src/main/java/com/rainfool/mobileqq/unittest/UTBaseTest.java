package com.rainfool.mobileqq.unittest;

import org.junit.runner.RunWith;
import org.robolectric.QQRobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * 单测专用基类
 * 指定QQRobolectric运行器，并设置UTQFixApplication作为单测application类，会执行onCreate逻辑
 *
 * 注意1：如果继承该类会导致一些初始化逻辑没有走到无法满足需求的情况，可以在你的类里指定如下配置
 *      @Config(application = UTQFixApplication.class, manifest = Config.NONE)
 *      但尽量能够用UTQFixApplication，耗时会有2～5秒的下降，且目前看能满足大部分诉求
 * 注意2：这里没有包含ShadowQRoute，如果业务验证逻辑需要mock QRoute.api返回，可以在你的类或方法上里指定如下配置
 *      @Config(shadows = ShadowQRoute.class)
 *
 */
@RunWith(QQRobolectricTestRunner.class)
public abstract class UTBaseTest {

}
