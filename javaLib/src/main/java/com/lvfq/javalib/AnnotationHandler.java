package com.lvfq.javalib;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.VariableElement;

/**
 * AnnotationHandler
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/5 下午11:58
 * @desc :
 */

public interface AnnotationHandler {
    void attachProcessingEnv(ProcessingEnvironment environment);

    Map<String, List<VariableElement>> handleAnnotation(RoundEnvironment environment);
}
