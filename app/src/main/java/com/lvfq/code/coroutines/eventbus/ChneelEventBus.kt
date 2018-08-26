package com.lvfq.code.coroutines.eventbus

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch


/**
 * ChneelEventBus
 * @author FaQiang on 2018/8/24 下午11:56
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */

val map = mutableMapOf<String, Channel<Any>>()

inline fun <reified T> T.post() {
    if (!map.containsKey(T::class.java.name)) {
        map.put(T::class.java.name, Channel())
    }
    launch(CommonPool) {
        map[T::class.java.name]?.send(this@post as Any)
    }
}

inline fun <T, reified R> T.onEvent(noinline action: suspend (R) -> Unit) {
    if (!map.containsKey(R::class.java.name)) {
        map.put(R::class.java.name, Channel())
    }

    launch(CommonPool) {
        val receive = map[R::class.java.name]?.receive()
        launch(UI) {
            action.invoke(receive as R)
        }
    }
}

class CBusBean

class CBusTwoBean