package com.lvfq.code.architectureComponents.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lvfq.code.R
import com.lvfq.code.architectureComponents.base.BaseHasChildActivity
import com.lvfq.code.architectureComponents.module.ActivityModule
import com.lvfq.code.architectureComponents.module.ViewModelFactory
import javax.inject.Inject

/**
 * LoginActivity
 * @author FaQiang on 2017/12/27 上午11:08
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class MainActivity : BaseHasChildActivity() {

    private val fragment1 = Fragment1.getInstanse()

    lateinit var module: ActivityModule
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_login)
        module = ViewModelProviders.of(this, factory).get(ActivityModule::class.java)
//        module = ViewModelFactory.get(ActivityModule::class.java)
        module.sendRequset(1)
        fragmentUtil.showFragment(fragment1, R.id.fl_controller)
    }
}