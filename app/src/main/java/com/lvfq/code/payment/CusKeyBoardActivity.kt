package com.lvfq.code.payment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_cus_keyboard.*

/**
 * CusKeyBoardActivity
 * @author FaQiang on 2018/7/10 上午10:11
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CusKeyBoardActivity : AppCompatActivity() {


    companion object {
        fun toIntent(activity: AppCompatActivity, isCustom: Boolean = false) {
            val intent = Intent(activity, CusKeyBoardActivity::class.java)
            intent.putExtra("isCustom", isCustom)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_keyboard)


        val isCustom = intent.getBooleanExtra("isCustom", false)
        if (isCustom) {
            ck_bv.bindView(ck_et)
            ck_bv.visibility = View.VISIBLE
            ck_kv.visibility = View.GONE
        } else {
            ck_bv.visibility = View.GONE
            ck_kv.visibility = View.VISIBLE
            ck_kv.bindView(ck_et)
        }
    }

}