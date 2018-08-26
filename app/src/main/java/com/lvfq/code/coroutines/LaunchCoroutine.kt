package com.lvfq.code.coroutines

import android.os.AsyncTask
import android.widget.TextView
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL
import kotlin.coroutines.experimental.CoroutineContext

/**
 * LaunchCoroutine
 * @author FaQiang on 2018/8/21 下午6:15
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
// 用于执行协程任务
fun launchCoroutine1() = launch {

}

// 用于执行协程任务， 通常只用于启动最外层协程
fun launchCoroutine2() = runBlocking {

}

// 用于执行协程任务，并得到执行结果
fun launchCoroutine3() = async {

}


private val mOkHttpClient = OkHttpClient()
val request = Request.Builder().url("https://baidu.com").get().build()

fun displayDashboard(textview: TextView) = runBlocking {
    launch(UI) {
        val result = async(AndroidCommonPool) {
            mOkHttpClient.newCall(request).execute().body()?.string()
        }.await()
        textview.text = result
    }
}

object AndroidCommonPool : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(block)
    }

}

/**
 *  suspend , 被 suspend 修饰的函数只能被有 suspend 修饰的函数调用。
 *
 *   因为 suspend 修饰的函数（或 lambda)被编译后会多一个参数类型叫 Continuation
 *   协程的异步调用本质上就是一次回调
 */

suspend fun getHtml(): String {
    return async { URL("https://www.baidu.com").readText() }.await()
}

fun <T : TextView> T.countDown() {
    launch(UI) {
        this@countDown.isSelected = true
        this@countDown.text = ""
        delay(1000)
    }
}