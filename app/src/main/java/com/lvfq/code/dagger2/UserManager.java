package com.lvfq.code.dagger2;

import android.util.Log;

import com.lvfq.library.utils.LvLog;

/**
 * UserManager
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/8 下午3:52
 * @desc :
 */

public class UserManager {

    private ApiService apiService;


    public UserManager(ApiService apiService, String url) {
        LvLog.i("UserManager: -----------");
        this.apiService = apiService;
    }

    public void regitster() {
        Log.i("UserManager", "regitster: ");

        apiService.register();
    }
}
