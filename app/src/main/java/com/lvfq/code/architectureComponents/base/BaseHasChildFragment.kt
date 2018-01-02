package com.lvfq.code.architectureComponents.base

import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * BaseHasChildFragment
 * @author FaQiang on 2018/1/2 下午10:29
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc : 如果 Fragment 中包含有 子Fragment ,并且 Fragment 使用了 dagger 注解 ，则需要继承当前类 作为 Base 类
 *
 */
open class BaseHasChildFragment : BaseFragment(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}