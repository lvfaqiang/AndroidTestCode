package com.lvfq.code.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * AnnotationTest1
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/7/26 下午1:53
 * @desc :
 */

public class AnnotationTest1 extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @OnCusBusEvent(tag = "" )
    private void mTest() {

    }
}
