package com.lvfq.code.architectureComponents.bean

import android.annotation.SuppressLint

/**
 * PostsBean
 * @author FaQiang on 2017/12/27 下午1:34
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@SuppressLint("ParcelCreator")
data class PostsBean(
        val id: Int,
        val url: String,
        val title: String,
        val excerpt: String,
        val date: String,
        val author: AuthorBean,
        val comment_count: Int,
        val comment_status: String
) : BaseBean()