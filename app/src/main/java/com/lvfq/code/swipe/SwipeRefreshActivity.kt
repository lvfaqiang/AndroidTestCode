package com.lvfq.code.swipe

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
//        mList.addAll(sendRequset())

        initAdapter()

        activity_swipe_refresh.initOptions(object : SwipeRefreshView.ConfigOptions {

            override fun configAdapter(): BaseQuickAdapter<*, BaseViewHolder> {
                /**
                 * 初始化 Adapter ,此处可以在 return 之前对 Adapter 进行一系列的需求配置。 比如 setEmptyView 等等~
                 *
                 * getAdapter() 得到的是此处设置的 adapter
                 */
                return mAdapter
            }

            override fun layoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(this@SwipeRefreshActivity)

            override fun configRecyclerView(recyclerView: RecyclerView) {
                /**
                 * 这里可进行一些你想要的 RecyclerView 设置， 此方法为非必选
                 *
                 * 不想实现此方法，也可通过 activity_swipe_refresh.getRecyclerView() 来获取 RecyclerView
                 */
                super.configRecyclerView(recyclerView)
            }

            override fun configSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) {
                /**
                 * 这里可进行一些你想要的 SwipeRefreshLayout 相关设置，比如刷新的颜色，背景色
                 *
                 * 此方法为非必选
                 *
                 * 不想实现此方法，也可通过 activity_swipe_refresh.getSwipeRefreshLayout() 来获取 SwipeRefreshLayout
                 */
                super.configSwipeRefreshLayout(refreshLayout)
            }

            override fun setEnableLoadMore(): Boolean {
                /**
                 * 是否可加载更多  该方法非必须，默认 false
                 *  等同于 setEnableRefresh(boolean)
                 */
                return super.setEnableLoadMore()
            }

            override fun setEnableRefresh(): Boolean {
                /**
                 * 是否可刷新  该方法非必须，默认 false
                 *  等同于 setEnableRefresh(boolean)
                 */
                return super.setEnableRefresh()
            }

        })

        // 是否可刷新，default false
        activity_swipe_refresh.setEnableRefresh(true)
        // 是否可加载更多，default false
        activity_swipe_refresh.setEnableLoadMore(true)

        // 设置空布局 要在 setAdapter  之后进行设置
//        mAdapter.setEmptyView(R.layout.layout_empty)// 可以直接通过当前 Adapter 引用进行设置，
//        activity_swipe_refresh.getAdapter().setEmptyView(R.layout.layout_empty)   // 也可以通过 getAdapter 进行设置
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