### QQUnitTest模块

背景：
在手Q工业化过程中，很多业务进行了模块下沉，相关单测也同步下沉至对应模块，但是模块里的单测无法直接依赖主工程的单测基类如QQRobolectricTestRunner。
为了方便大家在业务模块中编写单元测试，我们将单测依赖的通用类下沉至Foundation/QQUnitTest模块中。


- 本模块为单元测试通用类的下沉类，如QQRobolectricTestRunner、ShadowQRoute等，后续新增单测基础类可以添加到QQUnitTest模块的main目录；
- 其他模块只需通过testImplementation qf_unittest 即可使用该模块；
- Jmockit单测类中较为通用的MockUp类，在com.tencent.mobileqq.jmockit.mockuputils.*；
- 本模块为基础模块，**不允许依赖业务模块（包括接口及实现模块）**。可通过反射的方式进行类调用，可参考ShadowQRoute中反射调用的写法。


