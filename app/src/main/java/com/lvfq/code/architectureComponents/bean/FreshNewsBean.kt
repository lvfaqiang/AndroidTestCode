package com.lvfq.code.architectureComponents.bean

import android.annotation.SuppressLint

/**
 * FreshNewsBean
 * @author FaQiang on 2017/12/27 下午1:14
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@SuppressLint("ParcelCreator")
data class FreshNewsBean(
        val count_total: Int = 0,
        val pages: Int = 0,
        val count: Int = 0,
        val status: String = "",
        val posts: ArrayList<PostsBean>
) : BaseBean()


