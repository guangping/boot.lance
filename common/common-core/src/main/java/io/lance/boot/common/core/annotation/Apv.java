package io.lance.boot.common.core.annotation;

import java.lang.annotation.*;

/**
 * @desc: 测试注解
 * @author: lance
 * @time: 2017-10-20 15:44
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Apv {
}
