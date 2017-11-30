package com.lvfq.code.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.lvfq.code.R
import com.lvfq.library.utils.LvDPUtil

/**
 * MustTextView
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/2 下午3:17
 * @desc :
 *
 */
class MustTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val tv_content: TextView by lazy {
        TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
    }
    private val tv_symbol: TextView by lazy {
        TextView(context).apply {
            text = "﹡"
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(0, 0, LvDPUtil.dip2px(3f), 0)
            }

        }
    }
    private var textSize = TEXT_DEFAULT_SIZE
    private var textColor = TEXT_DEFAULT_COLOR
    private var symbolSize = TEXT_DEFAULT_SIZE
    private var symbolColor = SYMBOL_DEFAULT_COLOR
    private var content = ""
    private var isMust = true

    init {
        gravity = Gravity.CENTER_VERTICAL
        orientation = HORIZONTAL

        val ta = if (attrs == null) null else context.obtainStyledAttributes(attrs, R.styleable.MustTextView)
        if (ta != null) {
            // 这里如果自定义文字大小 sp 单位的话，这里会转换成对应的像素，根据手机密度放大指定倍数
            textSize = ta.getDimensionPixelSize(R.styleable.MustTextView_cus_textSize, TEXT_DEFAULT_SIZE)
            textColor = ta.getColor(R.styleable.MustTextView_cus_textColor, TEXT_DEFAULT_COLOR)
            symbolSize = ta.getDimensionPixelSize(R.styleable.MustTextView_cus_symbolSize, TEXT_DEFAULT_SIZE)
            symbolColor = ta.getColor(R.styleable.MustTextView_cus_symbolColor, SYMBOL_DEFAULT_COLOR)
            content = ta.getString(R.styleable.MustTextView_cus_text)
            isMust = ta.getBoolean(R.styleable.MustTextView_cus_isMust, isMust)

            ta.recycle()
        }

        isMust(isMust)

        tv_symbol.setTextColor(symbolColor)
        tv_symbol.setTextSize(TypedValue.COMPLEX_UNIT_PX, symbolSize.toFloat())
        tv_content.setTextColor(textColor)
        tv_content.text = content
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        addView(tv_symbol)
        addView(tv_content)
    }

    /**
     * set the label text
     *
     * @param charSequence
     */
    fun setText(charSequence: CharSequence) {
        tv_content.text = charSequence
    }

    fun setTextSize(size: Int) {
        tv_content.textSize = size.toFloat()
    }

    fun isMust(bool: Boolean) {
        isMust = bool
        tv_symbol.visibility = if (isMust) View.VISIBLE else View.GONE
    }

    companion object {

        private val TEXT_DEFAULT_SIZE = LvDPUtil.dip2px(14f)  // 默认是像素
        private val TEXT_DEFAULT_COLOR = 0x4a4a4a
        private val SYMBOL_DEFAULT_COLOR = 0xff7a7a
    }

}
