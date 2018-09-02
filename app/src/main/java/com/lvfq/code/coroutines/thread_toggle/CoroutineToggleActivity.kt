package com.lvfq.code.coroutines.thread_toggle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lvfq.code.R
import com.lvfq.library.utils.LvLog
import kotlinx.android.synthetic.main.activity_coroutine_toggle.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

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
        launch(AndroidCommonPool) {
            delay(4000)
            LvLog.i("lfq", "launch==========${Thread.currentThread().name}")
            launch(UI) {
                val result = kotlinx.coroutines.experimental.async {
                    delay(3000)
                    "resultString"
                }
                LvLog.i("lfq", "before ====${Thread.currentThread().name}")
                tv_content.text = result.await()
                LvLog.i("lfq", "after text:${tv_content.text}==== ${Thread.currentThread().name}")
                LvLog.i("lfq", "this Activity is Finished $isFinishing")
            }
        }

//        CoroutineJava.async(object : Task() {
//            override fun run() {
//                Thread.sleep(1000)
//                LvLog.i("lfq", "task==========${Thread.currentThread().name}")
//            }
//        })
    }

    fun button2(v: View) {
        CoroutineJava.ui(object : UI() {
            override fun run() {
                LvLog.i("lfq", "UI==========${Thread.currentThread().name}")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        LvLog.i("lfq", " OnDestroy")
    }
}