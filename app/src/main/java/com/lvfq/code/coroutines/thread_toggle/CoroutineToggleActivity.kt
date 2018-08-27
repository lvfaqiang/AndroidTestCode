package com.lvfq.code.coroutines.thread_toggle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lvfq.code.R
import com.lvfq.library.utils.LvLog

/**
 * CoroutineToggleActivity
 * @author FaQiang on 2018/8/27 上午10:18
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CoroutineToggleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_toggle)
        start()
        LvLog.i("lfq", "onCreate====================")
    }

    fun button1(v: View) {
        CoroutineJava.async(object : Task() {
            override fun run() {
                Thread.sleep(1000)
                LvLog.i("lfq", "task==========${Thread.currentThread().name}")
            }
        })
    }

    fun button2(v: View) {
        CoroutineJava.ui(object : UI() {
            override fun run() {
                LvLog.i("lfq", "UI==========${Thread.currentThread().name}")
            }
        })
    }
}