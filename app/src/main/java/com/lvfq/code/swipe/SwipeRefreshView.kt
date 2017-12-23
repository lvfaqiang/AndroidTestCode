package com.lvfq.code.swipe

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lvfq.code.R

/**
 * SwipeRefreshView
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/20 上午10:39
 * @desc : SwipeRefreshLayout + RecyclerView 结合 BaseRecyclerViewAdapterHelper
 *
 */
class SwipeRefreshView(context: Context) : FrameLayout(context), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    constructor(context: Context, attrs: AttributeSet) : this(context)

    private var swipeView: SwipeRefreshLayout = SwipeRefreshLayout(context)
    private var recyclerView: RecyclerView
    private lateinit var mAdapter: BaseQuickAdapter<*, BaseViewHolder>


    var swipeRefreshListener: SwipeRefreshListener? = null

    // 是否可刷新
    private var isCanRefresh = false
    // 是否可加载更多
    private var isCanLoadMore = false


    init {
        swipeView.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        recyclerView = RecyclerView(context)
        recyclerView.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        recyclerView.isNestedScrollingEnabled = false

        // 设置刷新的默认背景颜色
        swipeView.setProgressBackgroundColorSchemeResource(R.color.white)
        // 设置刷新的进度颜色
        swipeView.setColorSchemeResources(R.color.c_00ce9b, R.color.c_11c111, R.color.c_1495eb)

        swipeView.addView(recyclerView)
        swipeView.setOnRefreshListener(this)
        apply { layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT) }
        addView(swipeView)
    }

    /**
     * 初始化配置
     */
    fun initOptions(options: ConfigOptions) {
        recyclerView.layoutManager = options.layoutManager()
        // 对外提供 RecyclerView
        options.configRecyclerView(recyclerView)
        // 对外提供 SwipeRefreshLayout
        options.configSwipeRefreshLayout(swipeView)
        // 对内提供 Adapter
        mAdapter = options.configAdapter()
        initAdapter()
        // 是否可刷新
        setEnableRefresh(options.setEnableRefresh())
        // 是否可加载
        setEnableLoadMore(options.setEnableLoadMore())
    }

    /**
     * 获取 Adapter
     */
    fun getAdapter(): BaseQuickAdapter<*, BaseViewHolder> {
        checkInited()
        return mAdapter
    }

    // 获取 RecyclerView 实例
    fun getRecyclerView(): RecyclerView = recyclerView

    /**
     * 获取 SwipeRefreshLayout 实例
     */
    fun getSwipeRefreshLayout(): SwipeRefreshLayout = swipeView

    fun setProgressBackgroundColorSchemeColor(color: Int) {
        swipeView.setProgressBackgroundColorSchemeColor(color)
    }

    fun setBackgroudColor(color: Int) {
        recyclerView.setBackgroundColor(color)
    }

    /**
     * 设置 RecyclerView LayoutManager
     */
    private fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }

    /**
     * 初始化 Adapter
     */
    private fun initAdapter() {
//        recyclerView.layoutManager ?: let { throw NullPointerException("Please invoke the setLayoutManager method first") }
        mAdapter.setEnableLoadMore(isCanLoadMore)
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        recyclerView.adapter = mAdapter
    }


    // baseAdapter 刷新方法
    override fun onLoadMoreRequested() {
        swipeView.isEnabled = false
        if (swipeRefreshListener != null) {
            swipeRefreshListener?.onLoadMoreRequested()
        }
    }

    /**
     * 设置是否可刷新
     */
    fun setEnableRefresh(isCanRefresh: Boolean) {
        this.isCanRefresh = isCanRefresh
        swipeView.isEnabled = isCanRefresh
    }

    /**
     * 设置是否可加载更多
     */
    fun setEnableLoadMore(isCanLoadMore: Boolean) {
        this.isCanLoadMore = isCanLoadMore
        checkInited()
        mAdapter.setEnableLoadMore(isCanLoadMore)
    }

    /**
     * 设置空布局
     */
    fun setEmptyView(view: View) {
        checkInited()
        mAdapter.emptyView = view
    }

    /**
     * 设置空布局
     */
    fun setEmptyView(layoutId: Int) {
        checkInited()
        mAdapter.setEmptyView(layoutId)
    }

    /**
     * 加载到最后一页
     */
    fun loadMoreEnd() {
        checkInited()
        mAdapter.loadMoreEnd()
        reset()
    }


    /**
     * 加载到最后一页
     * 是否显示加载完成布局
     */
    fun loadMoreEnd(gone: Boolean) {
        checkInited()
        mAdapter.loadMoreEnd(gone)
        reset()
    }

    /**
     * 当前页加载完成，非最后一页
     */
    fun loadMoreComplete() {
        checkInited()
        mAdapter.loadMoreComplete()
        reset()
    }

    /**
     * 加载失败
     */
    fun loadMoreFail() {
        checkInited()
        mAdapter.loadMoreFail()
        reset()
    }

    // swipeRefreshLayout 刷新方法
    override fun onRefresh() {
        checkInited()
        mAdapter.setEnableLoadMore(false)
        if (swipeRefreshListener != null) {
            swipeRefreshListener?.onRefresh()
        }
    }

    private fun reset() {
        checkInited()
        mAdapter.setEnableLoadMore(isCanLoadMore)
        swipeView.isEnabled = isCanRefresh
        swipeView.isRefreshing = false
    }

    /**
     *
     */
    private fun checkInited() {
        if (!::mAdapter.isInitialized) {
            throw IllegalAccessException(" invoke the initOption method first please")
        }
    }

    interface SwipeRefreshListener {
        fun onRefresh()
        fun onLoadMoreRequested()
    }

    interface ConfigOptions {

        // 初始化 layoutmanager
        fun layoutManager(): RecyclerView.LayoutManager

        // 初始化 Adapter
        fun configAdapter(): BaseQuickAdapter<*, BaseViewHolder>

        // 获取当前 SwipeRefreshLayout
        fun configSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) = Unit

        // 获取当前的 RecyclerView
        fun configRecyclerView(recyclerView: RecyclerView) = Unit

        // 设置是否可刷新
        fun setEnableRefresh(): Boolean = false

        // 设置是否可加载更多
        fun setEnableLoadMore(): Boolean = false
    }
}