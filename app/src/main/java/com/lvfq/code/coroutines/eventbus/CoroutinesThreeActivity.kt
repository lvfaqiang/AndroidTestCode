package com.lvfq.code.coroutines.eventbus

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
class CoroutinesThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
        }
        val textView = TextView(this).apply {
            setPadding(20, 20, 20, 20)
            textSize = 14f
            setTextColor(resources.getColor(R.color.c_1495eb))
            gravity = Gravity.CENTER
            text = "this is Three 初始文本内容"
        }

        val button = Button(this).apply {
            text = "Three Btn"
            setAllCaps(false)
            gravity = Gravity.CENTER
            setPadding(20, 20, 20, 20)
        }

        linearLayout.addView(textView)
        linearLayout.addView(button)
        setContentView(linearLayout)

        button.setOnClickListener {
            CBusTwoBean().post()
            finish()
        }

    }

}