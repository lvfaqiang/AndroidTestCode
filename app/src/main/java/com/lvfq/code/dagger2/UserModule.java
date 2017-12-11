package com.lvfq.code.dagger2;

import com.lvfq.library.utils.LvLog;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * UserModule
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/8 下午12:39
 * @desc :
 */

@Module
public class UserModule {


//    public UserModule(Context context) {
//        this.context = context;
//    }
    //    @Provides
//    public ApiService provideApiService(String url) {
//        Log.i("UserModule", "provideApiService");
//        return new ApiService(url);
//    }

    @Provides
    public String provideUrl() {
        return "this is url";
    }

//    @Provides
//    public String provideUrl1() {
//        return "this is url2 -----";
//    }


    @Provides
    public ApiService provideApiService(OkHttpClient client) {
        return new ApiService(client);
    }

    /**
     * 这里的参数会从 当前 module 中查找 返回值为 String 的方法。拿到其结果。
     * 因为 ApiService 构造方法中也需要传入一个 String 类型的参数， 所以,就像上面描述的，从当前界面中寻找返回值为 String 类型的。
     *
     * @param apiService
     * @param url
     * @return
     */
    @Provides
    public UserManager provideUserManager(ApiService apiService, String url) {
        LvLog.i("provideUserManager: ");
        return new UserManager(apiService, url);
    }

}
