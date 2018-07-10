package com.lvfq.code.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_payment.*

/**
 * PaymentActivity
 * @author FaQiang on 2018/7/10 上午9:24
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class PaymentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        btn_1.setOnClickListener {
            CusKeyBoardActivity.toIntent(this, true)
        }

        btn_2.setOnClickListener {
            CusKeyBoardActivity.toIntent(this, false)
        }
    }
}