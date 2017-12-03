package com.lvfq.code.designpatterns.memo

import java.io.Serializable

/**
 * MemoBean
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/30 下午2:09
 * @desc :
 *
 */
data class MemoBean(
        var text: String = "",
        var cursor: Int = 0
) : Serializable