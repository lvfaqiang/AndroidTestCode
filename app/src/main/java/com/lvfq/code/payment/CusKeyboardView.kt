package com.lvfq.code.payment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.lvfq.code.R
import kotlinx.android.synthetic.main.layout_keyboard.view.*


/**
 * CusKeyboard
 * @author FaQiang on 2018/7/10 上午9:35
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CusKeyboardView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var editText: EditText? = null

    fun bindView(editText: EditText) {
        this.editText = editText
        hideSystemSoftKeyboard(editText)
    }

    init {
        View.inflate(context, R.layout.layout_keyboard, this)

        kb_tv_0.setOnClickListener(this)
        kb_tv_1.setOnClickListener(this)
        kb_tv_2.setOnClickListener(this)
        kb_tv_3.setOnClickListener(this)
        kb_tv_4.setOnClickListener(this)
        kb_tv_5.setOnClickListener(this)
        kb_tv_6.setOnClickListener(this)
        kb_tv_7.setOnClickListener(this)
        kb_tv_8.setOnClickListener(this)
        kb_tv_9.setOnClickListener(this)
        kb_tv_del.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v) {
            kb_tv_0 -> {
                insert("0")
            }
            kb_tv_1 -> {
                insert("1")
            }
            kb_tv_2 -> {
                insert("2")
            }
            kb_tv_3 -> {
                insert("3")
            }
            kb_tv_4 -> {
                insert("4")
            }
            kb_tv_5 -> {
                insert("5")
            }
            kb_tv_6 -> {
                insert("6")
            }
            kb_tv_7 -> {
                insert("7")
            }
            kb_tv_8 -> {
                insert("8")
            }
            kb_tv_9 -> {
                insert("9")
            }
            kb_tv_del -> {
                delete()
            }
        }
    }

    private fun insert(text: String) {
        editText?.let {
            it.editableText.insert(it.selectionStart, text)
        }
    }

    private fun delete() {
        editText?.let {
            val start = it.selectionStart
            if (start > 0) {
                it.editableText.delete(start - 1, start)
            }
        }
    }

}