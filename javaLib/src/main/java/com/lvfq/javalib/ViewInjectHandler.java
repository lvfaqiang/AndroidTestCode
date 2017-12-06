package com.lvfq.javalib;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

/**
 * ViewInjectHandler
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/6 上午9:08
 * @desc :
 */

public class ViewInjectHandler implements AnnotationHandler {
    ProcessingEnvironment mProcessingEnv;

    @Override
    public void attachProcessingEnv(ProcessingEnvironment environment) {
        mProcessingEnv = environment;
    }

    @Override
    public Map<String, List<VariableElement>> handleAnnotation(RoundEnvironment environment) {

        Map<String, List<VariableElement>> annotationMap = new HashMap<>();
        // 1 ， 获取 ViewInjector 注解的所有元素
        Set<? extends Element> elementSet = environment.getElementsAnnotatedWith(ViewInjector.class);
        for (Element element : elementSet) {
            // 2， 获取被注解的字段
            VariableElement varElement = (VariableElement) element;
            String className = varElement.getSimpleName().toString();
            List<VariableElement> cacheElements = annotationMap.get(className);
            if (cacheElements == null) {
                cacheElements = new LinkedList<>();
            }
        }

        return null;
    }
}
