package com.lvfq.code.architectureComponents.base

import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * BaseHasFragmentActivity
 * @author FaQiang on 2018/1/2 下午4:48
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc : 如果 Activity 中包含有 Fragment ,并且 Fragment 使用了 dagger 注解 ，则需要继承当前类 作为 Base 类
 *
 */
open class BaseHasChildActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}