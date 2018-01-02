package com.lvfq.code.architectureComponents

import com.lvfq.code.architectureComponents.ui.Fragment1
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * CommonModule
 * @author FaQiang on 2017/12/27 下午3:38
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Module
abstract class CommonModule {

    @ContributesAndroidInjector
    abstract fun contributeFragment(): Fragment1
}