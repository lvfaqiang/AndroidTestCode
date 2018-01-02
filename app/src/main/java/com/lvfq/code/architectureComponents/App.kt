package com.lvfq.code.architectureComponents

import android.app.Activity
import com.lvfq.code.MyApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * App
 * @author FaQiang on 2017/12/27 上午11:10
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *  注意如果当前没有 Fragment 使用，那么添加 HasSupportFragmentInjector 接口会报错，
 *  如果，某一个 Activity / Fragment 下包含的有 子 Fragment ,那么 其需要继承 BaseHasChildActivivy/ BaseHasChildFragment.
 *  并且，如果继承了 BaseHasChildActivity / BaseHasChildFragment ，但是界面中并未包含使用了 dagger 注解的子 Fragment，也会报错。
 *
 *  一般情况下， Application 中 只需要实现 HasActivityInjector 接口，当 某一个 Activity 中包含 Fragment 时，才需要实现接口
 */
class App : MyApplication(), HasActivityInjector/*, HasSupportFragmentInjector */ {

    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Activity>

//    @Inject
//    lateinit var fragmentInject: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInject
//    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInject
}