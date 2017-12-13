package com.lvfq.code.dagger2;

import android.app.Application;

import com.lvfq.library.utils.LvUtils;

/**
 * Dagger2Application
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/12 下午9:23
 * @desc :
 */

public class Dagger2Application extends Application {

    private HttpComponent httpComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        httpComponent = DaggerHttpComponent.create();
        LvUtils.init(this).initLog("lfq", true);
    }

    public HttpComponent getHttpComponent() {
        return httpComponent;
    }
}
