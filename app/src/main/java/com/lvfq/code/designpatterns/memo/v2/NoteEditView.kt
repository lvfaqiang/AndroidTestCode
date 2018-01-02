package com.lvfq.code.designpatterns.memo.v2

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.lvfq.code.designpatterns.memo.MemoBean

/**
 * NoteEditView
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/30 下午3:09
 * @desc :
 *
 */
class NoteEditView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatEditText(context, attrs, defStyleAttr) {

    constructor(
            context: Context,
            attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    fun createMemo(): MemoBean {
        return MemoBean(text.toString(), selectionStart)
    }

    fun restore(memoBean: MemoBean?) {
        setText(memoBean?.text ?: "")
        setSelection(memoBean?.cursor ?: 0)
    }
}