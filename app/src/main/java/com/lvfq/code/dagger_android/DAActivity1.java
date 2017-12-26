package com.lvfq.code.dagger_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lvfq.library.utils.LvLog;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import okhttp3.OkHttpClient;

/**
 * DaggerAndroidActivity1
 *
 * @author FaQiang on 2017/12/24 上午12:40
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 */

public class DAActivity1 extends AppCompatActivity {
    @Inject
    String string;

    @Inject
    DemoBean bean1;
    @Inject
    DemoBean bean2;

    @Inject
    OkHttpClient okHttpClient1;
    @Inject
    OkHttpClient okHttpClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        LvLog.i("D_A_Activity1", "onCreate: " + string);
        LvLog.i("D_A_Activity1", "onCreate: bean1" + bean1.toString());
        LvLog.i("D_A_Activity1", "onCreate: bean2" + bean2.toString());
        LvLog.i("D_A_Activity1", "onCreate: OkHttp" + okHttpClient.toString());
        LvLog.i("D_A_Activity1", "onCreate: OkHttp" + okHttpClient1.toString());
    }
}
