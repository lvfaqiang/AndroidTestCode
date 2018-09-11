package com.lvfq.code.animation

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.animation.Interpolator
import android.widget.TextView
import com.lvfq.code.R

/**
 * AnimationActivity
 * @author FaQiang on 2018/9/7 下午12:51
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView by lazy {
            TextView(this).apply {
                width = 150
                height = 150
                gravity = Gravity.CENTER
                textSize = 14f
                setTextColor(resources.getColor(R.color.c_1495eb))
            }
        }

        textView.text = "0"
        setContentView(textView)


        val anim = ValueAnimator.ofInt(1, 2, 3,4).setDuration(3000)
//        anim.repeatCount = INFINITE
        anim.interpolator = Interpolator {
            Log.i("TAG", "value: $it")
            it
        }

        anim.addUpdateListener {
            Log.i("TAG1", "value: ${it.animatedValue}")
            textView.text = it.animatedValue.toString()
        }

        anim.start()

        textView.setOnClickListener {
            if (anim.isStarted) {
                anim.cancel()
            } else {
                anim.start()
            }
        }

    }

}