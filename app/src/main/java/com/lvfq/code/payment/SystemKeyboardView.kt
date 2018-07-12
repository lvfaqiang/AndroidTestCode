package com.lvfq.code.payment

import android.content.Context
import android.graphics.drawable.Drawable
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import com.lvfq.code.R

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
//            setBackgroundResource(R.drawable.select_s_e3e7ed_n_white)
        }
    }


    private var mKeyBackground: Drawable
    private var mLabelTextSize: Int = 0
    private var mKeyTextSize: Int = 0
    private var mKeyTextColor: Int = 0
    private var mShadowRadius: Float = 0F
    private var mShadowColor: Int = 0

    init {
        mKeyBackground = resources.getDrawable(R.drawable.select_s_e3e7ed_n_white)  // 随便给了个默认值

        val a = context.obtainStyledAttributes(attrs, R.styleable.SystemKeyboardView)
        val n = a.indexCount

        for (i in 0 until n) {
            val attr = a.getIndex(i)

            when (attr) {
                R.styleable.SystemKeyboardView_keyBackground -> {
                    mKeyBackground = a.getDrawable(attr)
                }
//                R.styleable.SystemKeyboardView_verticalCorrection -> {
//                    mVerticalCorrection = a.getDimensionPixelOffset(attr, 0)
//                }
//                R.styleable.SystemKeyboardView_keyPreviewLayout -> {
//                    previewLayout = a.getResourceId(attr, 0)
//                }
//                R.styleable.SystemKeyboardView_keyPreviewOffset -> {
//                    mPreviewOffset = a.getDimensionPixelOffset(attr, 0)
//                }
//                R.styleable.SystemKeyboardView_keyPreviewHeight -> {
//                    mPreviewHeight = a.getDimensionPixelSize(attr, 80)
//                }
                R.styleable.SystemKeyboardView_keyTextSize -> {
                    mKeyTextSize = a.getDimensionPixelSize(attr, 18)
                }
                R.styleable.SystemKeyboardView_keyTextColor -> {
                    mKeyTextColor = a.getColor(attr, -0x1000000)
                }
                R.styleable.SystemKeyboardView_labelTextSize -> {
                    mLabelTextSize = a.getDimensionPixelSize(attr, 14)
                }
//                R.styleable.SystemKeyboardView_popupLayout -> {
//                    mPopupLayout = a.getResourceId(attr, 0)
//                }
                R.styleable.SystemKeyboardView_shadowColor -> {
                    mShadowColor = a.getColor(attr, 0)
                }
                R.styleable.SystemKeyboardView_shadowRadius -> {
                    mShadowRadius = a.getFloat(attr, 0f)
                }
            }
        }

        a.recycle()

        addView(keyboardView)
//        var mKeyBackground = getFieldValue(keyboardView, "mKeyBackground") as Drawable?
//        mKeyBackground = resources.getDrawable(R.drawable.select_s_e3e7ed_n_white)

        // 通过暴力反射 设置需要的属性
        setFieldValue(keyboardView, "mKeyBackground", mKeyBackground)
        setFieldValue(keyboardView, "mKeyTextSize", mKeyTextSize)
        setFieldValue(keyboardView, "mKeyTextColor", mKeyTextColor)
        setFieldValue(keyboardView, "mLabelTextSize", mLabelTextSize)
        setFieldValue(keyboardView, "mShadowColor", mShadowColor)
        setFieldValue(keyboardView, "mShadowRadius", mShadowRadius)


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

    fun getFieldValue(obj: Any?, fieldName: String): Any? {
        if (obj == null || TextUtils.isEmpty(fieldName)) {
            return null
        }

        val clazz: Class<*> = obj.javaClass
        while (clazz != Any::class.java) {
            try {
                val field = clazz.getDeclaredField(fieldName)
                field.isAccessible = true
                return field.get(obj)
            } catch (e: Exception) {
            }
        }
        return null
    }

    fun setFieldValue(obj: Any?, fieldName: String, value: Any) {
        if (obj == null || TextUtils.isEmpty(fieldName)) {
            return
        }
        val clazz: Class<*> = obj.javaClass
        while (clazz != Any::class.java) {
            try {
                val field = clazz.getDeclaredField(fieldName)
                field.isAccessible = true
                field.set(obj, value)
                return
            } catch (e: Exception) {
            }
        }
    }

}