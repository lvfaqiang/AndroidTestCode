package com.lvfq.code.architectureComponents.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ViewModuleFactory
 * @author FaQiang on 2018/1/2 上午10:34
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Singleton
class ViewModelFactory
@Inject constructor()
    : ViewModelProvider.Factory {

    @Inject
    lateinit var modules: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator = modules[modelClass]
        if (creator == null) {
            for ((key, value) in modules) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}