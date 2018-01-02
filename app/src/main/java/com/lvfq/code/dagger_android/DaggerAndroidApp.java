package com.lvfq.code.dagger_android;

import android.app.Activity;

import com.lvfq.code.MyApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * DaggerAndroidApp
 *
 * @author FaQiang on 2017/12/24 上午1:23
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc : 参考地址 ： <a href="http://blog.csdn.net/IO_Field/article/details/71730248" />
 * <p>
 * Dagger.android 启动类，相关 Component 为 {@link DAAppComponent}
 * <p>
 * 如果需要注入 Activity ，需要实现 HasActivityInjector
 * 并且 实现 activityInjector() 方法。
 * 注入 v4.Fragment  ， 需要实现 HasSupportFragmentInjector
 * 实现 supportFragmentInjector() 方法,
 * <p>
 * 这里只是测试，就只注入了 Activity 类，
 * 一般情况下， Application 中 只需要实现 HasActivityInjector 接口，
 * 当 某一个 Activity 中包含 Fragment 时，才需要在 该 Activity 中 再实现 HasSupportFragmentInjector接口
 */

public class DaggerAndroidApp extends MyApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDAAppComponent.builder().create(this).inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

}
