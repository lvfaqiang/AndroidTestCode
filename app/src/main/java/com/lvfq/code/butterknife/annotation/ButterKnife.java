package com.lvfq.code.butterknife.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ButterKnife
 *
 * @author FaQiang on 2018/6/21 下午1:40
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */
public class ButterKnife {

    public static void bind(Activity activity) {
        try {
            bindView(activity);
            bindOnClick(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void bindView(Activity activity) throws Exception {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 允许暴力反射（可操作 private 修饰属性）
            declaredField.setAccessible(true);

            BindView annotation = declaredField.getAnnotation(BindView.class);
            if (annotation != null) {
                int id = annotation.value();
                View viewById = activity.findViewById(id);
                declaredField.set(activity, viewById);
            }
        }
    }

    private static void bindOnClick(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation != null) {
                int id = annotation.value();
                View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        }
    }

}
