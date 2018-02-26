package com.lvfq.code.custom.column_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * CustomViewCanvas
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/10/23 下午2:14
 * @desc :
 */

public class CustomViewCanvas extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom_canvas);

        CanvasTestView1 view = new CanvasTestView1(this);
        setContentView(view);

    }
}
