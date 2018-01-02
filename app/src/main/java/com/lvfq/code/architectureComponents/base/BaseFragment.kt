package com.lvfq.code.architectureComponents.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * BaseFragment
 * @author FaQiang on 2018/1/2 下午1:09
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
open class BaseFragment : Fragment() {

    protected open fun isUseDagger(): Boolean = true

    override fun onAttach(activity: Activity?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            if (isUseDagger()) AndroidSupportInjection.inject(this)
        }
        super.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            if (isUseDagger()) AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }
}