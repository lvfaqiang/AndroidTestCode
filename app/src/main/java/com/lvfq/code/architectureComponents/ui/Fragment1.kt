package com.lvfq.code.architectureComponents.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lvfq.code.R
import com.lvfq.code.architectureComponents.base.BaseFragment
import com.lvfq.code.architectureComponents.bean.PostsBean
import com.lvfq.code.architectureComponents.module.ActivityModule
import com.lvfq.code.swipe.SwipeRefreshView
import com.lvfq.library.utils.LvLog

/**
 * Fragment1
 * @author FaQiang on 2017/12/27 下午10:30
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class Fragment1 : BaseFragment() {

    companion object {
        fun getInstanse(): Fragment1 {
            return Fragment1()
        }
    }

    private var mAdapter: BaseQuickAdapter<PostsBean, BaseViewHolder>? = null
    private var posts = ArrayList<PostsBean>()

    private lateinit var swipeView: SwipeRefreshView
    private lateinit var module: ActivityModule

//    @Inject
//    lateinit var factory: ViewModelFactory

    /**
     *  如果 当前界面要使用 Dagger, 请注意 App 中的描述
     *  并且 如果用 dagger 的话，需要 CommonModule 中注释的 Fragment1 相关的两行代码放开。
     */
    override fun isUseDagger(): Boolean {
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        swipeView = SwipeRefreshView(context)

        return swipeView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        module = ViewModelProviders.of(activity, factory).get(ActivityModule::class.java)
        module = ViewModelProviders.of(activity).get(ActivityModule::class.java)
//        module = ViewModelFactory.get(ActivityModule::class.java)
        // 初始化数据
        if (null != module.getDatas().value) {
            posts.addAll(module.getDatas().value!!)
        }
        initAdapter()

        module.getDatas().observe(this, Observer<ArrayList<PostsBean>> {
            LvLog.i("界面刷新。。。${it?.size}")
            if (null != it) {
                posts.addAll(it)
            }
            swipeView.loadMoreComplete()
            mAdapter?.notifyDataSetChanged()
        })
        swipeView.initOptions(object : SwipeRefreshView.ConfigOptions {
            override fun layoutManager(): RecyclerView.LayoutManager {
                return LinearLayoutManager(activity)
            }

            override fun configAdapter(): BaseQuickAdapter<*, BaseViewHolder> {
                return mAdapter ?: throw NullPointerException()
            }

            override fun setEnableLoadMore(): Boolean {
                return true
            }
        })
        swipeView.swipeRefreshListener = object : SwipeRefreshView.SwipeRefreshListener {
            override fun onRefresh() {

            }

            override fun onLoadMoreRequested() {
                module.sendRequset(2)
            }

        }
    }

    private fun initAdapter() {
        mAdapter = object : BaseQuickAdapter<PostsBean, BaseViewHolder>(R.layout.item_fragment1, posts) {
            override fun convert(helper: BaseViewHolder, item: PostsBean) {
                val tv_title: TextView = helper.getView(R.id.item_f1_tv_title)
                val tv_date: TextView = helper.getView(R.id.item_f1_tv_date)
                val tv_dec: TextView = helper.getView(R.id.item_f1_tv_dec)

                tv_title.text = item.title
                tv_date.text = item.date
                tv_dec.text = item.excerpt
            }

        }
    }
}