package com.lvfq.code.swipe

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lvfq.code.R
import kotlinx.android.synthetic.main.activity_swipe_refresh.*

/**
 * SwipeRefreshActivity
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/11/20 上午11:12
 * @desc :
 *
 */
class SwipeRefreshActivity : AppCompatActivity(), SwipeRefreshView.SwipeRefreshListener {

    private lateinit var mAdapter: BaseQuickAdapter<String, BaseViewHolder>
    private var mList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh)

        initView()
    }

    private fun getList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 10) {
            list.add("item$i")
        }
        return list
    }

    private fun initView() {
        // 模拟页面初始化数据请求，
        onRefresh()
//        mList.addAll(getList())

        initAdapter()
        activity_swipe_refresh.setLayoutManager(LinearLayoutManager(this))

        activity_swipe_refresh.setAdapter(mAdapter as BaseQuickAdapter<Any, BaseViewHolder>)

        // 是否可刷新，default true
        activity_swipe_refresh.setEnableRefresh(true)
        // 是否可加载更多，default true
        activity_swipe_refresh.setEnableLoadMore(true)

        // 设置空布局 要在 setAdapter  之后进行设置
//        mAdapter.setEmptyView(R.layout.layout_empty)// 可以直接通过当前 Adapter 引用进行设置，
        activity_swipe_refresh.setEmptyView(R.layout.layout_empty)

        //设置监听
        activity_swipe_refresh.swipeRefreshListener = this
    }

    private fun initAdapter() {
        mAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, mList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                if (helper != null) {
                    val textView: TextView = helper.getView(android.R.id.text1)
                    textView.apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)// 这里高度值是像素
                        gravity = Gravity.CENTER_VERTICAL
                    }
                    textView.text = item
                }
            }
        }
    }

    override fun onRefresh() {
        // 刷新
        pageIndex = 1
        Handler().postDelayed({
            mAdapter.setNewData(getList())
            activity_swipe_refresh.loadMoreComplete()
        }, 1000)
    }

    private var pageIndex = 1

    override fun onLoadMoreRequested() {
        // 加载更多。
        Handler().postDelayed({
            pageIndex++
            mAdapter.addData(getList())
            if (pageIndex <= 2) {
                activity_swipe_refresh.loadMoreComplete()
            } else {
                activity_swipe_refresh.loadMoreEnd(false)
            }
        }, 1000)
    }
}