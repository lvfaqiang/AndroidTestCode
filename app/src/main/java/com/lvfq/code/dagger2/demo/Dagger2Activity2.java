package com.lvfq.code.dagger2.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lvfq.code.dagger2.ApiService;
import com.lvfq.code.dagger2.Dagger2Application;
import com.lvfq.library.utils.LvLog;

import javax.inject.Inject;

/**
 * Dagger2Activity2
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/12 下午11:06
 * @desc : 测试 Singleton 注解是否是全局唯一。
 */

public class Dagger2Activity2 extends AppCompatActivity {

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerComponent2.builder().httpComponent(((Dagger2Application) getApplication()).getHttpComponent()).build().init(this);
        // 输出结果和 Dagger2Activity 中的 ApiService 地址相同，标明是全局唯一。
        LvLog.i("onCreate: Dagger2Activity2 :" + apiService);
    }
}
