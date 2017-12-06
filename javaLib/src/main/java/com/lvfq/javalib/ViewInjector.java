package com.lvfq.javalib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ViewInjector
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 下午10:13
 * @desc : 新建一个注解类
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface ViewInjector {
    int value();
}
