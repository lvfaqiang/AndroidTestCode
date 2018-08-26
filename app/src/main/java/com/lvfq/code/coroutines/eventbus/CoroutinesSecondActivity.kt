package com.lvfq.code.coroutines.eventbus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.lvfq.code.R

/**
 * CoroutinesMainActivity
 * @author FaQiang on 2018/8/24 下午11:24
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CoroutinesSecondActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
        }
        textView = TextView(this).apply {
            setPadding(20, 20, 20, 20)
            textSize = 14f
            setTextColor(resources.getColor(R.color.c_1495eb))
            gravity = Gravity.CENTER
            text = "初始文本内容"
        }

        val button = Button(this).apply {
            text = "Second Next"
            setAllCaps(false)
            gravity = Gravity.CENTER
            setPadding(20, 20, 20, 20)
        }

        linearLayout.addView(textView)
        linearLayout.addView(button)
        setContentView(linearLayout)

        button.setOnClickListener {
            CBusBean().post()
            startActivity(Intent(this, CoroutinesThreeActivity::class.java))
        }
        onEvent()
    }

    fun onEvent() {
        onEvent { it: CBusTwoBean ->
            textView.text = "这是事件通知后的文本内容"
        }

    }

}