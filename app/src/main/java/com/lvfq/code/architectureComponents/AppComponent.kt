package com.lvfq.code.architectureComponents

import com.lvfq.code.architectureComponents.http.ClientModule
import com.lvfq.code.architectureComponents.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * AppComponent
 * @author FaQiang on 2017/12/27 下午5:05
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Singleton
@Component(modules = [
    (AppModuleAbs::class),
    (ClientModule::class),
    (ViewModelModule::class)
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}