package com.lvfq.code.dagger2;

import com.lvfq.library.utils.LvLog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * HttpModule
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/9 上午11:16
 * @desc :
 */

@Module
public class HttpModule {

//    @Singleton
    @Provides
    public OkHttpClient provideOkHttp() {
        LvLog.i("provideOkHttp: ");
        return new OkHttpClient.Builder().build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(OkHttpClient okHttpClient) {
        LvLog.i("provideApiService: ");
        return new ApiService(okHttpClient);
    }

}
