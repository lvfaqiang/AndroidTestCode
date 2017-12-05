package com.lvfq.code;

import android.support.multidex.MultiDexApplication;

import com.lvfq.library.utils.LvUtils;

/**
 * MyApplication
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/1 下午11:15
 * @desc :
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        LvUtils.init(this).initLog("lfq", true);
    }
}
