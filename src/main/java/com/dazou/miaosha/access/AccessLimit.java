package com.dazou.miaosha.access;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 20:31 2019-04-14
 */



@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
    int seconds();
    int maxCount();
    boolean needLogin() default true;
}
