package com.lvfq.code.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lvfq.code.R
import com.lvfq.library.utils.LvDPUtil
import java.math.BigDecimal


/**
 * CircleProgressBar
 * @author FaQiang on 2018/7/4 下午4:22
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class CircleProgressBar constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private val MAX = 360
    private val START_ANGLE = 270

    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private lateinit var mPaint: Paint
    private var strokeWidth = 5//线条宽度
    private var rectF: RectF? = null
    private var normalColor = Color.parseColor("#A5A5A5")//普通的颜色
    private var progressColor = Color.parseColor("#FA9025")//已经走了的进度条颜色
    private var completeColor = 0
    private var textColor = normalColor//文字颜色
    private var textSize = 20f//文字大小
    private var progress = 0//进度条
    private var centerText: String? = ""//中心填充文字
    private lateinit var fontPaint: Paint
    private var progress_style = Paint.Style.STROKE//填充式还是环形式

    internal var fontMetrics: Paint.FontMetrics? = null

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar)
        textSize = array.getDimension(R.styleable.CircleProgressBar_text_size, textSize)
        textColor = array.getColor(R.styleable.CircleProgressBar_text_color, textColor)
        centerText = if (array.getString(R.styleable.CircleProgressBar_text) == null) centerText else array.getString(R.styleable.CircleProgressBar_text)
        strokeWidth = array.getInt(R.styleable.CircleProgressBar_stroke_width, strokeWidth)
        normalColor = array.getColor(R.styleable.CircleProgressBar_normal_color, normalColor)
        progressColor = array.getColor(R.styleable.CircleProgressBar_progress_color, progressColor)
        completeColor = array.getColor(R.styleable.CircleProgressBar_complete_color, completeColor)
        progress = array.getInt(R.styleable.CircleProgressBar_progress, progress)
        progress_style = if (array.getInt(R.styleable.CircleProgressBar_progress_style, 0) == 0) Paint.Style.STROKE else Paint.Style.FILL

        array.recycle()

        if (progress > MAX) {
            progress = MAX
        }

        if (completeColor == 0) {
            completeColor = progressColor
        }
        strokeWidth = LvDPUtil.dip2px(strokeWidth.toFloat())

        initPaint()
    }

    // 2.初始化画笔
    private fun initPaint() {
        mPaint = Paint()
        mPaint.color = normalColor       //设置画笔颜色
        mPaint.isAntiAlias = true
        mPaint.style = progress_style  //设置画笔模式为描边
        mPaint.strokeWidth = strokeWidth.toFloat()         //设置画笔宽度为10px

        fontPaint = Paint()
        fontPaint.textSize = textSize
        fontPaint.isAntiAlias = true
        fontPaint.color = textColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)

        if (mHeight > mWidth)
        //高大于宽的情况
        {
            rectF = RectF(strokeWidth.toFloat(), (mHeight / 2 - mWidth / 2 + strokeWidth).toFloat(), (mWidth - strokeWidth).toFloat(), (mHeight / 2 + mWidth / 2 - strokeWidth).toFloat())
        } else if (mWidth > mHeight)
        //宽大于高的情况
        {
            rectF = RectF((mWidth / 2 - mHeight / 2 + strokeWidth).toFloat(), strokeWidth.toFloat(), (mWidth / 2 + mHeight / 2 - strokeWidth).toFloat(), (mHeight - strokeWidth).toFloat())
        } else
        //宽等于高的情况
        {
            rectF = RectF(strokeWidth.toFloat(), strokeWidth.toFloat(), (mWidth - strokeWidth).toFloat(), (mHeight - strokeWidth).toFloat())
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.color = normalColor
        mPaint.shader = null
        if (progress < MAX) {
            canvas.drawArc(rectF, (START_ANGLE + progress).toFloat(), (MAX - progress).toFloat(), progress_style === Paint.Style.FILL, mPaint)
        }
        if (progress == MAX) {
            mPaint.color = completeColor
        } else {
            mPaint.color = progressColor
        }
        val colors = intArrayOf(0xFF5A66E8.toInt(), 0xFFDF97FF.toInt())

        val shader = LinearGradient(0f, 0f, mWidth.toFloat(), mHeight.toFloat(), colors, null, Shader.TileMode.CLAMP)
        mPaint.shader = shader
        canvas.drawArc(rectF, START_ANGLE.toFloat(), progress.toFloat(), progress_style === Paint.Style.FILL, mPaint)

        fontMetrics = fontPaint.fontMetrics
        val textWidth = fontPaint.measureText(centerText)
        val textHeight = fontPaint.ascent() + fontPaint.descent()
        canvas.drawText(centerText, mWidth / 2 - textWidth / 2, mHeight / 2 - textHeight / 2, fontPaint)
    }


    /**
     * 更新界面
     * @param progress
     * @param text
     */
    fun update(progressCount: String, totalCount: String = MAX.toString(), text: String = "") {
        val rate = getRate(progressCount, totalCount)
        this.progress = rate.multiply(BigDecimal(MAX)).toInt()

        if (text.isEmpty()) {
            this.centerText = "${rate.multiply(BigDecimal(100)).toInt()}%"
        } else {
            this.centerText = text
        }

        postInvalidate()
    }

    /**
     * 更新界面
     * @param progressRate 进度 （0.xx）
     * @param text 中心内容
     */
    fun updateRate(progressRate: String, text: String = "") {
        this.progress = BigDecimal(progressRate).multiply(BigDecimal(MAX)).toInt()
        if (text.isEmpty()) {
            this.centerText = "${BigDecimal(progressRate).multiply(BigDecimal(100)).toInt()}%"
        } else {
            this.centerText = text
        }
        postInvalidate()
    }

    private fun getRate(progress: String, total: String): BigDecimal {
        try {
            val pro = BigDecimal(progress)
            val t = BigDecimal(total)
            return pro.divide(t, 4, BigDecimal.ROUND_DOWN)
        } catch (e: Exception) {
            return BigDecimal(0)
        }
    }


}