package com.lvfq.code.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * CoroutinesActivity
 * @author FaQiang on 2018/8/9 上午12:52
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CoroutinesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

    }

    fun test1(v: View) {
        displayDashboard(c_tv_content)
    }

    fun test2(v: View) {
        launch(UI) {
            c_tv_content.text = getHtml()
        }

    }

    fun test3(v: View) {

    }

}