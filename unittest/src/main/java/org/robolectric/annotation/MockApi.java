package org.robolectric.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要mock的QRouteApi接口
 * 列表形式加入
 * 目前仅支持在单测用例类中声明
 * 注解声明后会自动添加与移除，见{@link org.robolectric.checker.MockApiChecker}
 * mock方式是通过shadow类拦截处理，见{@link org.robolectric.shadowes.ShadowQRoute}
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MockApi {

    /**
     * 需要mock的QRouteApi接口列表
     * @return
     */
    Class<?>[] api() default {};
}
