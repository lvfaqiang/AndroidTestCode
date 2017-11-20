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
 * @desc :
 *
 */
class SwipeRefreshView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    var swipeView: SwipeRefreshLayout = SwipeRefreshLayout(context)
    private var recyclerView: RecyclerView
    private lateinit var mAdapter: BaseQuickAdapter<Any, BaseViewHolder>


    var swipeRefreshListener: SwipeRefreshListener? = null

    // 是否可刷新
    private var isCanRefresh = true
    // 是否可加载更多
    private var isCanLoadMore = true


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
     * 获取 Adapter
     */
    fun getAdapter(): BaseQuickAdapter<Any, BaseViewHolder> = mAdapter

    // 获取 RecyclerView
    fun getRecyclerView(): RecyclerView = recyclerView

    fun setProgressBackgroundColorSchemeColor(color: Int) {
        swipeView.setProgressBackgroundColorSchemeColor(color)
    }

    fun setBackgroudColor(color: Int) {
        recyclerView.setBackgroundColor(color)
    }

    /**
     * 设置 RecyclerView LayoutManager
     */
    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }

    /**
     * 设置 Adapter 并初始化
     */
    fun setAdapter(adapter: BaseQuickAdapter<Any, BaseViewHolder>) {
        mAdapter = adapter
        initAdapter()
    }

    /**
     * 初始化 Adapter
     */
    private fun initAdapter() {
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
        mAdapter.setEnableLoadMore(isCanLoadMore)
    }

    /**
     * 设置空布局
     */
    fun setEmptyView(view: View) {
        mAdapter.emptyView = view
    }

    /**
     * 设置空布局
     */
    fun setEmptyView(layoutId: Int) {
        mAdapter.setEmptyView(layoutId)
    }

    /**
     * 加载到最后一页
     */
    fun loadMoreEnd() {
        mAdapter.loadMoreEnd()
        reset()
    }


    /**
     * 加载到最后一页
     * 是否显示加载完成布局
     */
    fun loadMoreEnd(gone: Boolean) {
        mAdapter.loadMoreEnd(gone)
        reset()
    }

    /**
     * 当前页加载完成，非最后一页
     */
    fun loadMoreComplete() {
        mAdapter.loadMoreComplete()
        reset()
    }

    /**
     * 加载失败
     */
    fun loadMoreFail() {
        mAdapter.loadMoreFail()
        reset()
    }

    // swipeRefreshLayout 刷新方法
    override fun onRefresh() {
        mAdapter.setEnableLoadMore(false)
        if (swipeRefreshListener != null) {
            swipeRefreshListener?.onRefresh()
        }
    }

    private fun reset() {
        mAdapter.setEnableLoadMore(isCanLoadMore)
        swipeView.isEnabled = isCanRefresh
        swipeView.isRefreshing = false
    }

    interface SwipeRefreshListener {
        fun onRefresh()
        fun onLoadMoreRequested()
    }
}