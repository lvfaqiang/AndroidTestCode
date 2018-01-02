package com.lvfq.code.architectureComponents.bean

import android.annotation.SuppressLint

/**
 * AuthorBean
 * @author FaQiang on 2017/12/27 下午1:34
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@SuppressLint("ParcelCreator")
data class AuthorBean(
        val id: Int,
        val slug: String,
        val name: String,
        val first_name: String,
        val last_name: String,
        val nickname: String,
        val url: String,
        val description: String
) : BaseBean()