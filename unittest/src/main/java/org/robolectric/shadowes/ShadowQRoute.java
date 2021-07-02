package org.robolectric.shadowes;

import com.tencent.mobileqq.qroute.QRoute;

import org.mockito.Mockito;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.util.ReflectionHelpers;

import java.util.HashMap;
import java.util.HashSet;

/**
 * QRoute的Shadow类，只要接入{@link org.robolectric.QQRobolectricTestRunner}就默认添加
 * 无需在@Config(shadows)中添加
 *
 * 针对QRoute.api提供mock对象
 * 1. DynamicFeature模块的api接口可添加到{@link ShadowQRoute#mockClassMap}中
 * 比如 IMiniAppService
 *
 * 2. 普通模块的api接口如需提供mock对象，在用例中声明注解{@link org.robolectric.annotation.MockApi}
 * 例子：
 *
 * @MockApi(api = {IDPCApi.class})
 * public class ThreadOptimizerTest{
 * ....
 * }
 * ShadowQRoute就会自动为用例mock掉指定的api，并在用例结束时移除mock记录。
 *
 * 3. PowerMock用例需要使用ShadowQRoute，需要将QRoute加到PowerMockIgnore中
 * 例子：
 * @PowerMockIgnore({"com.tencent.mobileqq.qroute.QRoute"}) 或，PowerMock直接mockStatic掉QRoute.api
 */
@Implements(QRoute.class)
public class ShadowQRoute {

    /**
     * 需要提供mock对象的集合
     * 以类的名称作为key值
     */
    static HashSet<String> mockClassMap = new HashSet<String>();

    /**
     * 需要默认mock的接口在此处添加
     * 如非DynamicFeature模块的接口请谨慎添加
     */
    static {
        mockClassMap.add("com.tencent.mobileqq.mini.api.IMiniAppService");
        mockClassMap.add("com.tencent.mobileqq.weiyun.api.IWeiyunHelper");
        mockClassMap.add("com.tencent.mobileqq.apollo.statistics.product.api.IApolloDtReportHelper");
        mockClassMap.add("com.tencent.biz.pubaccount.api.IPublicAccountProxy");
        mockClassMap.add("com.tencent.biz.pubaccount.api.IPublicAccountServlet");
        mockClassMap.add("com.tencent.biz.pubaccount.api.IPublicAccountObserver");
        mockClassMap.add("com.tencent.biz.pubaccount.util.api.IPublicAccountMessageUtil");
        mockClassMap.add("com.tencent.biz.pubaccount.api.IPublicAccountReportUtils");
        mockClassMap.add("com.tencent.mobileqq.nearby.api.IHotChatUtil");
        mockClassMap.add("com.tencent.mobileqq.nearby.api.INearbyService");
        mockClassMap.add("com.tencent.mobileqq.nearby.api.INearbyFlowerUtil");
        mockClassMap.add("com.tencent.mobileqq.nearby.api.INearbyManagerHelper");
        mockClassMap.add("com.tencent.mobileqq.hotchat.api.IHotChatApi");
        mockClassMap.add("com.tencent.mobileqq.nearby.api.IFactoryApi");
        mockClassMap.add("com.tencent.mobileqq.qwallet.hb.grap.voice.IVoiceRedPacketHelper");
        mockClassMap.add("com.tencent.mobileqq.qwallet.hb.IQWalletHbApi");
        mockClassMap.add("com.tencent.mobileqq.qwallet.IQWalletGdtAdApi");
        mockClassMap.add("com.tencent.mobileqq.qwallet.transaction.INotifyMsgApi");
        mockClassMap.add("com.tencent.mobileqq.qwallet.IQWalletPayApi");
        mockClassMap.add("cooperation.qwallet.plugin.IQWalletHelper");
        mockClassMap.add("com.tencent.mobileqq.qwallet.ipc.IComIPCUtils");
        mockClassMap.add("cooperation.groupvideo.api.IGroupVideoHelper");
        mockClassMap.add("com.tencent.mobileqq.intervideo.IAppSettingUtil");
        mockClassMap.add("com.tencent.mobileqq.intervideo.IBaseApplicationImplUtil");
        mockClassMap.add("com.tencent.aelight.camera.api.IAEClassManager");
        mockClassMap.add("com.tencent.mobileqq.gamecenter.api.IGameMsgHandlerNameApi");
        mockClassMap.add("com.tencent.mobileqq.apollo.game.api.ICmGameHelper");
        mockClassMap.add("com.tencent.mobileqq.qqexpand.manager.IExpandManagerProxy");
        mockClassMap.add("com.tencent.mobileqq.studyroom.api.IStudyRoomMisc");
        mockClassMap.add("com.tencent.mobileqq.vas.download.api.IDownloaderFactory");
    }

    /**
     * mock对象集合
     * 目前仅支持单例的api接口
     */
    static HashMap<Class, Object> mockObjMap = new HashMap();

    @Implementation
    public static <T> T api(Class<T> clazz) {
        if (mockClassMap.contains(clazz.getName())) {
            if (mockObjMap.keySet().contains(clazz) && mockObjMap.get(clazz) != null) {
                return (T) mockObjMap.get(clazz);
            } else {
                T mock;
                if (clazz.getName().equals("com.tencent.mobileqq.weiyun.api.IWeiyunHelper")) {
                    //mock = (T) new ShadowWeiyunHelper();    //mock掉某些默认实现
                    String clazzName = "com.tencent.common.app.ShadowWeiyunHelper";
                    try {
                        Class<?> clz = Class.forName(clazzName);
                        mock = (T) clz.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                        mock = null;
                    }
                } else if (clazz.getName().equals("cooperation.qwallet.plugin.IQWalletHelper")) {
                    //mock = (T) new ShadowQWalletHelper();
                    String clazzName = "com.tencent.common.app.ShadowQWalletHelper";
                    try {
                        Class<?> clz = Class.forName(clazzName);
                        mock = (T) clz.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                        mock = null;
                    }
                } else if (clazz.getName().equals("cooperation.groupvideo.api.IGroupVideoHelper")) {
                    //mock = (T) new ShadowGroupVideoHelper();
                    String clazzName = "com.tencent.common.app.ShadowGroupVideoHelper";
                    try {
                        Class<?> clz = Class.forName(clazzName);
                        mock = (T) clz.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                        mock = null;
                    }
                } else {
                    mock = Mockito.mock(clazz);
                }
                mockObjMap.put(clazz, mock);
                return mock;
            }
        } else if (clazz.getName().matches("com\\.tencent\\.mobileqq\\.kandian.*\\.api.*")) {
            // 看点目录下的代码统一 mock
            T mock;
            if (mockObjMap.keySet().contains(clazz) && mockObjMap.get(clazz) != null) {
                mock = (T) mockObjMap.get(clazz);
            } else {
                mock = Mockito.mock(clazz);
                mockObjMap.put(clazz, mock);
            }
            return mock;
        } else {
            // 没在需要mock对象集合中，调用原方法
            return Shadow.directlyOn(QRoute.class, "api", ReflectionHelpers.ClassParameter.from(Class.class, clazz));
        }
    }

    /**
     * 将需要mock的类加入{@link ShadowQRoute#mockClassMap}
     */
    public static void addMockClass(Class clazz) {
        mockClassMap.add(clazz.getName());
    }

    /**
     * 将需要mock的类从{@link ShadowQRoute#mockClassMap}移除
     */
    public static void removeMockClass(Class clazz) {
        mockClassMap.remove(clazz.getName());
    }
}
