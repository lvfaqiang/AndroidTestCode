package com.lvfq.code.dagger2.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * CusAnnotation
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/12 上午12:01
 * @desc :
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface CusAnnotation {
}
