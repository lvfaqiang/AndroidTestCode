package com.lvfq.code.architectureComponents.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lvfq.library.utils.FragmentUtil
import dagger.android.AndroidInjection

/**
 * BaseActivity
 * @author FaQiang on 2017/12/27 下午1:51
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
open class BaseActivity : AppCompatActivity() {

    protected lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        fragmentUtil = FragmentUtil(supportFragmentManager)
    }

}