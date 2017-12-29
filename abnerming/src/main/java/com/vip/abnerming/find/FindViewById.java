package com.vip.abnerming.find;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengjin on 17/3/14.
 * 注解各个控件
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FindViewById {
    /**
     * 保存view控件的id
     *
     * @return view控件id
     */
    int value();

}
