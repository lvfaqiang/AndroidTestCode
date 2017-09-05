package com.lvfq.code.annotation;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * CusBus
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/7/26 下午2:04
 * @desc :
 */

public class CusBus {
    private static CusBus instance = null;
    private static ConcurrentMap<Class, List> map = new ConcurrentHashMap<>();
    private static Map<Integer, Class> classes = new HashMap<>();

    public static CusBus getInstance() {
        if (instance == null) {
            synchronized (CusBus.class) {
                if (instance == null) {
                    instance = new CusBus();
                }
            }
        }
        return instance;
    }

    public void register(Fragment fragment) {
//        classes.add(fragment.getClass());
        classes.put(fragment.hashCode(), fragment.getClass());

    }

    public void register(Activity activity) {
        classes.put(activity.hashCode(), activity.getClass());
    }

    public void execute(Class<? extends Annotation> clazz) {

//        Class clazz = subscriber.getClass();
//        List<Method> methodList = new ArrayList<>();
//        Method[] methods = clazz.getMethods();
//        if (methods != null && methods.length > 0) {
//            for (Method method : methods) {
//                if (method.isAnnotationPresent(OnCusBusEvent.class)) {
//                    methodList.add(method);
//                }
//            }
//            if (methodList.size() > 0) {
//                map.put(clazz, methodList);
//            }
//        }

//        if (methods != null && methods.length > 0) {
//            for (Method method : methods) {
//                if (method.isAnnotationPresent(clazz)) {
//                    try {
//                        method.invoke(subscriber, (Object[]) new Objects[]{});
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
