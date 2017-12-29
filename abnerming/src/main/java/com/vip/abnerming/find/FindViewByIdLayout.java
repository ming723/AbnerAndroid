package com.vip.abnerming.find;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengjin on 17/3/14.
 * 注解layout
 */

@Target(ElementType.TYPE)//ElementType.TYPE只能在类中使用此注解
@Retention(RetentionPolicy.RUNTIME)// @Retention(RetentionPolicy.RUNTIME) 注解可以在运行时通过反射获取一些信息
@Documented
public @interface FindViewByIdLayout {
    int value();
}
