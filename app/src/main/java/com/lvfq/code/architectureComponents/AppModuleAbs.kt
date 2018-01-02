package com.lvfq.code.architectureComponents

import android.app.Application
import com.lvfq.code.architectureComponents.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * AppModuleAbs
 * @author FaQiang on 2017/12/27 上午11:09
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class AppModuleAbs {

    @Binds
    @Singleton
    abstract fun application(app: App): Application


    @ContributesAndroidInjector(modules = [CommonModule::class])
    abstract fun loginActivity(): MainActivity
}
