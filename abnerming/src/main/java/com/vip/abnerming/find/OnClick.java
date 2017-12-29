package com.vip.abnerming.find;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengjin on 17/3/14.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    /**
     * 保存所有需要设置点击事件控件的id
     *
     * @return
     */
    int[] value();
}
