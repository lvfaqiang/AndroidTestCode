package com.lvfq.code.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lvfq.code.R;
import com.lvfq.code.dagger2.annotation.CusAnnotation;
import com.lvfq.code.dagger2.demo.Dagger2Activity2;
import com.lvfq.library.utils.LvLog;

import javax.inject.Inject;
import javax.inject.Named;

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

    //    @Named("release")
    @Inject
    ApiService mApiService;

    //    @Named("dev")
    @Inject
    ApiService apiService;

//    @Inject
//    UserManager userManager;

    @Named("testUrl")// 用于区分不同对象的注解，需要在 Module 中添加相同注解，进行对应。
    @Inject
    String url;


    @CusAnnotation  // 自定义区分对象的注解，也是需要在 Module中添加相同注解。
    @Inject
    String url1;

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
//        DaggerComponent.builder()
// //                .userModule(new UserModule())
//                .httpComponent(DaggerHttpComponent.create())  // 调用方式一
//                .httpComponent(DaggerHttpComponent.builder().httpModule(new HttpModule()).build())  // 调用方式二
//                .build().init(this);

//        当 HttpComponent 为 单例模式时，
        DaggerComponent.builder()
                .userModule(new UserModule())
                .httpComponent(((Dagger2Application) getApplication()).getHttpComponent())
                .build().init(this);

        LvLog.i("onCreate: mApiService" + mApiService);
        LvLog.i("onCreate: apiService" + apiService);

//        userManager.regitster();
//        mApiService.register();
        LvLog.i("onCreate: " + url + "\n url1 : " + url1);

        findViewById(R.id.tv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dagger2Activity.this, Dagger2Activity2.class));
            }
        });
    }

}
