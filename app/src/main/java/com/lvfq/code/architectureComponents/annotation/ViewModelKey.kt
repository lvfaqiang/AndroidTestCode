package com.lvfq.code.architectureComponents.annotation

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * ViewModelKey
 * @author FaQiang on 2018/1/2 上午10:40
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)