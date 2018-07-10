package com.lvfq.code.payment

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.lvfq.code.R
import kotlinx.android.synthetic.main.view_verify_code.view.*


/**
 * Created by wxf on 2018/4/16.
 */

class VerifyCodeView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private val editText: EditText
    private val textViews: Array<TextView>
    var editContent: String? = null
        private set


    private var inputCompleteListener: InputCompleteListener? = null

    init {
        View.inflate(context, R.layout.view_verify_code, this)
        textViews = arrayOf(
                tv_0,
                tv_1,
                tv_2,
                tv_3,
                tv_4,
                tv_5
        )
        editText = findViewById(R.id.edit_text_view)

        editText.isCursorVisible = false//隐藏光标
        setEditTextListener()
    }

    private fun setEditTextListener() {
        editText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                editContent = editText.text.toString()

                if (inputCompleteListener != null) {
                    if (editContent!!.length >= MAX) {
                        inputCompleteListener!!.inputComplete()
                    } else {
                        inputCompleteListener!!.invalidContent()
                    }
                }

                for (i in 0 until MAX) {
                    if (i < editContent!!.length) {
//                        textViews[i].text = editContent!![i].toString()
                        textViews[i].draw(R.drawable.shape_solid_black_dot, Position.LEFT)
                    } else {
//                        textViews[i].text = ""
                        textViews[i].draw(null, Position.LEFT)
                    }
                }
            }
        })
    }

    fun clear() {
        textViews.map {
            it.text = ""
            it.draw(null, Position.LEFT)
        }
        editContent = ""
        editText.setText("")
    }

    fun focus() {
        editText.requestFocus()
    }

    fun setInputCompleteListener(inputCompleteListener: InputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener
    }

    interface InputCompleteListener {

        fun inputComplete()

        fun invalidContent()
    }

    companion object {
        private val MAX = 6
    }
}
