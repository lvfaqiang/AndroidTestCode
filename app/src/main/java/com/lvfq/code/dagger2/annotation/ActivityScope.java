package com.lvfq.code.dagger2.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ActivityScope
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/12 下午9:43
 * @desc :
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {}
