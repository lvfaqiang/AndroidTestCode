package com.lvfq.code.payment

import android.content.Context
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import com.lvfq.code.R
import java.lang.reflect.Method

/**
 * SystemKeyboardView
 * @author FaQiang on 2018/7/10 下午1:12
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class SystemKeyboardView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var editText: EditText? = null

    fun bindView(editText: EditText) {
        this.editText = editText
        hideSystemSoftKeyboard(editText)
    }

    private val keyboardView by lazy {
        KeyboardView(context, attrs).apply {
            layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
    }


    init {

        addView(keyboardView)

        keyboardView.keyboard = Keyboard(context, R.xml.key_board_number)
        keyboardView.isPreviewEnabled = false
        keyboardView.setOnKeyboardActionListener(object : KeyboardView.OnKeyboardActionListener {
            override fun swipeRight() {
            }

            override fun onPress(primaryCode: Int) {
            }

            override fun onRelease(primaryCode: Int) {
            }

            override fun swipeLeft() {
            }

            override fun swipeUp() {
            }

            override fun swipeDown() {
            }

            override fun onText(text: CharSequence?) {
            }

            override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
                editText?.let {
                    val start = it.selectionStart
                    when (primaryCode) {
                        Keyboard.KEYCODE_DELETE -> {
                            if (start > 0) {
                                it.editableText.delete(start - 1, start)
                            } else {

                            }
                        }
                        else -> {
                            it.editableText.insert(start, Character.toString(primaryCode.toChar()))
                        }
                    }
                }
            }

        })
    }

    fun hideSystemSoftKeyboard(editText: EditText) {
        val sdkInt = Build.VERSION.SDK_INT
        if (sdkInt >= 11) {
            try {
                val cls = EditText::class.java
                val setShowSoftInputOnFocus: Method
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
                setShowSoftInputOnFocus.isAccessible = true
                setShowSoftInputOnFocus.invoke(editText, false)
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {
            editText.inputType = InputType.TYPE_NULL
        }
    }
}