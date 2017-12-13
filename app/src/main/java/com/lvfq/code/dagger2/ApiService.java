package com.lvfq.code.dagger2;

import android.util.Log;

import com.lvfq.library.utils.LvLog;

import okhttp3.OkHttpClient;

/**
 * ApiService
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/8 下午12:43
 * @desc :
 */

public class ApiService {

//    @Inject
//    public ApiService() {
//        Log.i("ApiService", "ApiService:------------ ");
//    }

    OkHttpClient okHttpClient;

    //    @Inject
    public ApiService(OkHttpClient okHttpClient) {
        LvLog.i("ApiService: ");
        this.okHttpClient = okHttpClient;
    }

    //    @Inject
//    public ApiService(String url) {
//        Log.i("ApiService", "ApiService:------------ " + url);
//    }

    public void register() {
        Log.i("ApiService", "register: ");
    }
}
