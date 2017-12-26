package com.lvfq.code.dagger_android;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;

/**
 * DAAppModule
 *
 * @author FaQiang on 2017/12/26 上午12:45
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 * 在 Application 直接关联的 Module中，必须要有一个包含 AndroidInjectionModule，
 * 并且，必须要有一个 抽象方法，进行绑定 Application ,就比如 当前 module 中 返回值为 Application 的方法。
 */

@Module(includes = {AndroidInjectionModule.class})
public abstract class DAAppModule {
    // 此方法在和 Application 直接相关的 Module 中 必须存在一个，不然会出现 Application 找不到的问题，
    @Binds
    @Singleton
    public abstract Application application(DaggerAndroidApp app);

//    @ContributesAndroidInjector(modules = {DAModule1.class/* 这里的 module 是和 该抽象方法返回的 Activity/Fragment 直接绑定的， 这种写法编译自动生成Component */})
//    abstract DAActivity1 contributeMainActivity();
    // 此类写法 等同于 新建一个 Subcomponent ，然后关联 DAActivity1 和 Module
}
