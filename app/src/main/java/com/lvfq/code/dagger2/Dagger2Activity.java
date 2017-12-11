package com.lvfq.code.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lvfq.code.R;

import javax.inject.Inject;

/**
 * Dagger2Activity
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/8 上午11:19
 * @desc :
 */

public class Dagger2Activity extends AppCompatActivity {

//    @Inject
//    ApiService mApiService;

    @Inject
    UserManager userManager;

    @Inject
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注入方式一 只适合单个 Module
//        DaggerComponent.create().init(this);

        /**
         * 注入方式二
         * 等价于 DaggerComponent.create().init(this);
         * 此方式适合 Module 需要传递参数
         */
        DaggerComponent.builder()
                .userModule(new UserModule())
//                .httpComponent(DaggerHttpComponent.create())  // 调用方式一
                .httpComponent(DaggerHttpComponent.builder().httpModule(new HttpModule()).build())  // 调用方式二
                .build().init(this);
//
        userManager.regitster();
//        mApiService.register();

        Log.i("Dagger2Activity", "onCreate: " + url);
    }
}
