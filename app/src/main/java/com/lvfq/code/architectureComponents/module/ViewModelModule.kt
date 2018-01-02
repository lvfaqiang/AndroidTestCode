package com.lvfq.code.architectureComponents.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lvfq.code.architectureComponents.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ViewModelModule
 * @author FaQiang on 2017/12/28 下午11:54
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ActivityModule::class)
    abstract fun bindActivityModule(activityModule: ActivityModule): ViewModel

    @Binds
    abstract fun bindsModuleFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}