package com.lvfq.code.architectureComponents.module

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.lvfq.code.architectureComponents.bean.PostsBean
import com.lvfq.code.architectureComponents.repository.ListRepository
import com.lvfq.library.utils.LvLog
import javax.inject.Inject

/**
 * ActivityModule
 * @author FaQiang on 2017/12/27 下午7:01
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class ActivityModule @Inject constructor(val repository: ListRepository) : ViewModel() {
    private var datas: LiveData<ArrayList<PostsBean>>
    private val page = MutableLiveData<Int>()

    init {
        LvLog.i("------------init")
        datas = Transformations.switchMap(page) { input -> repository.getResult(configParams(input)) }
    }

    fun sendRequset(pageNum: Int) {
//        datas = repository.getResult()
        LvLog.i("------------sendRequest")
        page.value = pageNum
    }

    fun remove(bean: PostsBean) {
        datas.value?.remove(bean)
    }

    fun getDatas(): LiveData<ArrayList<PostsBean>> {
        return datas
    }

    private fun configParams(pageNum: Int): HashMap<String, Any> {
        LvLog.i("------------configParams")
        return hashMapOf("oxwlxojflwblxbsapi" to "get_recent_posts",
                "include" to "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields",
                "page" to pageNum,
                "custom_fields" to "thumb_c,views",
                "dev" to "1")
    }

}