package com.lvfq.code.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import com.lvfq.code.R
import java.util.*


/**
 * PagerSlidingTabStrip2018/12/24 上午9:28
 * @desc :
 *
 */
class PagerSlidingTabStrip(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : HorizontalScrollView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val mContext = context

    interface IconTabProvider {
        fun getPageIconResId(position: Int): Int
    }


    private val tabsContainer: LinearLayout by lazy {
        LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        }
    }

    private val defaultTabLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
    private val expandedTabLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f)
    private val expandedTabLayoutParamsWrap: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f)

    private val pageListener = PageListener()
    var delegatePageListener: ViewPager.OnPageChangeListener? = null

    private var pager: ViewPager? = null

    private var pageCount: Int = 0

    private var currentPosition = 0
    private var currentPositionOffset = 0f

    private val rectPaint: Paint
    private val dividerPaint: Paint

    private var checkedTabWidths = false

    private var indicatorColor = -0x7ee4    // 指示器颜色
    private var underlineColor = -0x313132    // 下划线颜色
    private var dividerColor = 0xFF811C    // 分割线颜色
    private var indicatorPadding = 10f  //指示器 左右内边距

    private var shouldExpand = false//FF811C
    private var textAllCaps = false
    private var isFixedIndicatorWidth = false

    private var scrollOffset = 52
    private var indicatorHeight = 0    // 指示器高度
    private var indicatorWidth = 0  // 指示器宽度
    private var underlineHeight = 0    // 下划线高度
    private var dividerPadding = 6 // 分割线
    private var tabPadding = 20
    private var tabPaddingr = 0   // 选中的tab padding
    private var dividerWidth = 1
    private var tabTextSize = 16   // 默认文字大小
    private var tabTextColor = -0x666667  // 默认颜色

    private var tabTextSizer = 16  // 选中的文字大小
    private var tabTextColorr = -0x1 // 选中颜色
    private var tabTypeface: Typeface? = null
    private var tabTypefaceStyle = Typeface.NORMAL

    private var lastScrollX = 0
    private var tabBackgroundResId: Int = 0
    private var locale: Locale? = null

    init {
        isFillViewport = true
        setWillNotDraw(false)

        addView(tabsContainer)

        val dm = resources.displayMetrics

        scrollOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset.toFloat(), dm).toInt()
        indicatorHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight.toFloat(), dm).toInt()
        indicatorWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorWidth.toFloat(), dm).toInt()
        underlineHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight.toFloat(), dm).toInt()
        dividerPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding.toFloat(), dm).toInt()
        tabPaddingr = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPaddingr.toFloat(), dm).toInt()
        indicatorPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorPadding, dm)
        dividerWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth.toFloat(), dm).toInt()
        tabTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSize.toFloat(), dm).toInt()
        tabTextSizer = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSizer.toFloat(), dm).toInt()


        context.withStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip) {
            indicatorColor = getColor(R.styleable.PagerSlidingTabStrip_indicatorColor, indicatorColor)
            indicatorPadding = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_indicatorPaddingLeftRight, indicatorPadding.toInt()).toFloat()
            underlineColor = getColor(R.styleable.PagerSlidingTabStrip_underlineColor, underlineColor)
            dividerColor = getColor(R.styleable.PagerSlidingTabStrip_dividerColor, dividerColor)
            indicatorHeight = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_indicatorHeight, indicatorHeight)
            indicatorWidth = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_indicatorWidth, indicatorWidth)
            underlineHeight = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_underlineHeight, underlineHeight)
            dividerPadding = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_dividerPadding, dividerPadding)
            tabPadding = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_tabPaddingLeftRight, tabPadding)
            tabBackgroundResId = getResourceId(R.styleable.PagerSlidingTabStrip_tabBackground, tabBackgroundResId)
            shouldExpand = getBoolean(R.styleable.PagerSlidingTabStrip_shouldExpand, shouldExpand)
            isFixedIndicatorWidth = getBoolean(R.styleable.PagerSlidingTabStrip_isFixedIndicatorWidth, isFixedIndicatorWidth)
            scrollOffset = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_scrollOffset, scrollOffset)
//            textAllCaps = getBoolean(R.styleable.PagerSlidingTabStrip_textAllCaps, textAllCaps);
            tabTextSize = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_tabDefTextSize, tabTextSize)
            tabTextSizer = getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_tabSelTextSize, tabTextSizer)
            tabTextColor = getColor(R.styleable.PagerSlidingTabStrip_tabDefTextColor, tabTextColor)
            tabTextColorr = getColor(R.styleable.PagerSlidingTabStrip_tabSelTextColor, tabTextColorr)
        }

        rectPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        dividerPaint = Paint().apply {
            isAntiAlias = true
            strokeWidth = dividerWidth.toFloat()
        }
        if (locale == null) {
            locale = resources.configuration.locale
        }
    }

    /**
     * 这里如果有需要可以设置一个回调接口
     *
     * @param pager
     */
    fun setViewPager(pager: ViewPager) {
        this.pager = pager

        if (pager.adapter == null) {
            throw IllegalStateException(
                    "ViewPager does not have adapter instance.")
        }
        pager.addOnPageChangeListener(pageListener)

        notifyDataSetChanged()
    }

    fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        this.delegatePageListener = listener
    }

    fun notifyDataSetChanged() {

        tabsContainer.removeAllViews()

        pageCount = pager?.adapter?.count ?: 0

        for (i in 0 until pageCount) {

            if (pager?.adapter is IconTabProvider) {
                addIconTab(i,
                        (pager?.adapter as IconTabProvider)
                                .getPageIconResId(i))
            } else {
                addTextTab(i, pager?.adapter?.getPageTitle(i).toString())
            }

        }

        updateTabStyles()

        checkedTabWidths = false

        viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {

                    @SuppressLint("NewApi")
                    override fun onGlobalLayout() {

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            viewTreeObserver.removeGlobalOnLayoutListener(
                                    this)
                        } else {
                            viewTreeObserver.removeOnGlobalLayoutListener(
                                    this)
                        }

                        currentPosition = pager?.currentItem ?: 0
                        scrollToChild(currentPosition, 0)
                    }
                })

    }

    private fun addTextTab(position: Int, title: String) {

        val tab = TextView(mContext).apply {
            text = title
            isFocusable = true
            gravity = Gravity.CENTER
            setSingleLine()
        }

        addTab(position, tab)
    }

    private fun addIconTab(position: Int, resId: Int) {

        val tab = ImageButton(context)
        tab.isFocusable = true
        tab.setImageResource(resId)

        addTab(position, tab)
    }

    private fun addTab(position: Int, tab: View) {
        tab.setOnClickListener {
            pager?.setCurrentItem(position, false)
        }

        tab.setPadding(tabPadding, 0, tabPadding, 0)
        tabsContainer.addView(tab)
//        tabsContainer.addView(tab, position, if (shouldExpand) expandedTabLayoutParams else defaultTabLayoutParams)
    }

    private fun updateTabStyles() {
        val currentIndex = this.pager?.currentItem
        for (i in 0 until pageCount) {

            val v = tabsContainer.getChildAt(i)

            v.setBackgroundResource(tabBackgroundResId)

            if (v is TextView) {

                v.setTypeface(tabTypeface, tabTypefaceStyle)
                v.setPadding(tabPadding, 0, tabPadding, 0)
                if (currentIndex == i) {
                    v.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizer.toFloat())
                    v.setTextColor(tabTextColorr)
                } else {
                    v.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize.toFloat())
                    v.setTextColor(tabTextColor)
                }
                // setAllCaps() is only available from API 14, so the upper case
                // is made manually if we are on a
                // pre-ICS-build
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT.compareTo(Build.VERSION_CODES.ICE_CREAM_SANDWICH) >= 0) {
                        v.setAllCaps(true)
                    } else {
                        v.text = locale?.let {
                            v.text.toString()
                                    .toUpperCase(it)
                        }
                    }
                }
            }
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        if (!shouldExpand || View.MeasureSpec.getMode(widthMeasureSpec) == View.MeasureSpec.UNSPECIFIED) {
//            return
//        }

        val myWidth = measuredWidth
        var childWidth = 0
        var maxItemWidth = 0
        for (i in 0 until pageCount) {
            val itemChildWidth = tabsContainer.getChildAt(i).measuredWidth + (tabPadding * 2)
            maxItemWidth = if (maxItemWidth > itemChildWidth) maxItemWidth else itemChildWidth
            childWidth += itemChildWidth
        }

        if (!checkedTabWidths && childWidth > 0 && myWidth > 0) {
            val lp = if (childWidth > myWidth) defaultTabLayoutParams else {
//                if (myWidth / pageCount < maxItemWidth) expandedTabLayoutParamsWrap else
                expandedTabLayoutParams
            }

            for (i in 0 until pageCount) {
                tabsContainer.getChildAt(i).layoutParams = lp
            }
            checkedTabWidths = true
        }
    }

    private fun scrollToChild(position: Int, offset: Int) {

        if (pageCount == 0) {
            return
        }

        var newScrollX = tabsContainer.getChildAt(position).left + offset

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX
            scrollTo(newScrollX, 0)
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isInEditMode || pageCount == 0) {
            return
        }

        val height = height

        // draw indicator line

        rectPaint.color = indicatorColor

        // default: line below current tab
        val currentTab = tabsContainer.getChildAt(currentPosition)
        var lineLeft = currentTab.left.toFloat()
        var lineRight = currentTab.right.toFloat()

        // if there is an offset, start interpolating left and right coordinates
        // between current and next tab
        if (currentPositionOffset > 0f && currentPosition < pageCount - 1) {

            val nextTab = tabsContainer.getChildAt(currentPosition + 1)
            val nextTabLeft = nextTab.left.toFloat()
            val nextTabRight = nextTab.right.toFloat()

            lineLeft = currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft
            lineRight = currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight
        }
        if (isFixedIndicatorWidth) {
            indicatorPadding = (lineRight - lineLeft - indicatorWidth) / 2
        }
        canvas.drawRect(lineLeft + indicatorPadding, (height - indicatorHeight).toFloat(), lineRight - indicatorPadding, height.toFloat(),
                rectPaint)

        // draw underline

        rectPaint.color = underlineColor
        canvas.drawRect(0f, (height - underlineHeight).toFloat(), tabsContainer.width.toFloat(),
                height.toFloat(), rectPaint)

        // draw divider

        dividerPaint.color = dividerColor
        for (i in 0 until pageCount - 1) {
            val tab = tabsContainer.getChildAt(i)
            canvas.drawLine(tab.right.toFloat(), dividerPadding.toFloat(), tab.right.toFloat(),
                    (height - dividerPadding).toFloat(), dividerPaint)
        }
    }

    private inner class PageListener : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float,
                                    positionOffsetPixels: Int) {

            currentPosition = position
            currentPositionOffset = positionOffset

            scrollToChild(position, (positionOffset * tabsContainer
                    .getChildAt(position).width).toInt())

            invalidate()

            delegatePageListener?.onPageScrolled(position, positionOffset,
                    positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager?.currentItem ?: 0, 0)
            }

            delegatePageListener?.onPageScrollStateChanged(state)
        }

        override fun onPageSelected(position: Int) {
            delegatePageListener?.onPageSelected(position)
            updateTabStyles()
        }

    }


    fun setIndicatorColor(indicatorColor: Int) {
        this.indicatorColor = indicatorColor
        invalidate()
    }

    fun setIndicatorColorResource(resId: Int) {
        this.indicatorColor = resources.getColor(resId)
        invalidate()
    }

    fun getIndicatorColor(): Int {
        return indicatorColor
    }

    fun setIndicatorHeight(indicatorLineHeightPx: Int) {
        this.indicatorHeight = indicatorLineHeightPx
        invalidate()
    }

    fun getIndicatorHeight(): Int {
        return indicatorHeight
    }

    fun setUnderlineColor(underlineColor: Int) {
        this.underlineColor = underlineColor
        invalidate()
    }

    fun setUnderlineColorResource(resId: Int) {
        this.underlineColor = resources.getColor(resId)
        invalidate()
    }

    fun getUnderlineColor(): Int {
        return underlineColor
    }

    fun setDividerColor(dividerColor: Int) {
        this.dividerColor = dividerColor
        invalidate()
    }

    fun setDividerColorResource(resId: Int) {
        this.dividerColor = resources.getColor(resId)
        invalidate()
    }

    fun getDividerColor(): Int {
        return dividerColor
    }

    fun setUnderlineHeight(underlineHeightPx: Int) {
        this.underlineHeight = underlineHeightPx
        invalidate()
    }

    fun getUnderlineHeight(): Int {
        return underlineHeight
    }

    fun setDividerPadding(dividerPaddingPx: Int) {
        this.dividerPadding = dividerPaddingPx
        invalidate()
    }

    fun getDividerPadding(): Int {
        return dividerPadding
    }

    fun setScrollOffset(scrollOffsetPx: Int) {
        this.scrollOffset = scrollOffsetPx
        invalidate()
    }

    fun getScrollOffset(): Int {
        return scrollOffset
    }

    fun setShouldExpand(shouldExpand: Boolean) {
        this.shouldExpand = shouldExpand
        requestLayout()
    }

    fun getShouldExpand(): Boolean {
        return shouldExpand
    }

    fun isTextAllCaps(): Boolean {
        return textAllCaps
    }

    fun setAllCaps(textAllCaps: Boolean) {
        this.textAllCaps = textAllCaps
    }

    fun setTextSize(textSizePx: Int) {
        this.tabTextSize = textSizePx
        updateTabStyles()
    }

    fun getTextSize(): Int {
        return tabTextSize
    }

    fun setTextColor(textColor: Int) {
        this.tabTextColor = textColor
        updateTabStyles()
    }

    fun setTextColorResource(resId: Int) {
        this.tabTextColor = resources.getColor(resId)
        updateTabStyles()
    }

    fun getTextColor(): Int {
        return tabTextColor
    }

    fun setTypeface(typeface: Typeface, style: Int) {
        this.tabTypeface = typeface
        this.tabTypefaceStyle = style
        updateTabStyles()
    }

    fun setTabBackground(resId: Int) {
        this.tabBackgroundResId = resId
    }

    fun getTabBackground(): Int {
        return tabBackgroundResId
    }

    fun setTabPaddingLeftRight(paddingPx: Int) {
        this.tabPadding = paddingPx
        updateTabStyles()
    }

    fun getTabPaddingLeftRight(): Int {
        return tabPadding
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        currentPosition = savedState.currentPosition
        requestLayout()
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val savedState = SavedState(superState)
        savedState.currentPosition = currentPosition
        return savedState
    }

    internal class SavedState : View.BaseSavedState {
        var currentPosition: Int = 0

        constructor(superState: Parcelable) : super(superState)

        private constructor(source: Parcel) : super(source) {
            currentPosition = source.readInt()
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(currentPosition)
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }


    inline fun Context.withStyledAttributes(
            set: AttributeSet? = null,
            attrs: IntArray,
            @AttrRes defStyleAttr: Int = 0,
            @StyleRes defStyleRes: Int = 0,
            block: TypedArray.() -> Unit
    ) {
        val typedArray = obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
        try {
            typedArray.block()
        } finally {
            typedArray.recycle()
        }
    }
}