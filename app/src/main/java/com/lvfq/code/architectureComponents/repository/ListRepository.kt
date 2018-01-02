package com.lvfq.code.architectureComponents.repository

import android.arch.lifecycle.LiveData
import com.lvfq.code.architectureComponents.Constants
import com.lvfq.code.architectureComponents.bean.FreshNewsBean
import com.lvfq.code.architectureComponents.bean.PostsBean
import com.lvfq.code.architectureComponents.http.ApiService
import com.lvfq.code.architectureComponents.http.NetworkBoundResource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * ListRepository
 * @author FaQiang on 2018/1/2 上午12:55
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class ListRepository @Inject constructor(val apiService: ApiService) {

//    fun getResult(): MutableLiveData<ArrayList<PostsBean>> {
//        val datas = MutableLiveData<ArrayList<PostsBean>>()
//        send(apiService.getFreshNews(Constants.sApi, "get_recent_posts",
//                "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields",
//                1, "thumb_c,views", "1"), object : Observer<FreshNewsBean> {
//            override fun onError(e: Throwable?) {
//
//            }
//
//            override fun onNext(value: FreshNewsBean?) {
//                LvLog.i("onNext ${value is Parcelable}")
////                fragment1.setData(value?.posts ?: ArrayList<PostsBean>())
//                datas.value = value?.posts
//            }
//
//            override fun onComplete() {
//            }
//
//            override fun onSubscribe(d: Disposable?) {
//
//            }
//        })
//        return datas
//    }

    fun getResult(params: HashMap<String, Any>): LiveData<ArrayList<PostsBean>> {
        return object : NetworkBoundResource<ArrayList<PostsBean>, FreshNewsBean>() {
            override fun createCall(): Observable<FreshNewsBean> {
                return apiService.getFreshNews(Constants.sApi, params)
            }

            override fun processResponse(response: FreshNewsBean): ArrayList<PostsBean>? {
                return response.posts
            }

            override fun saveCallResult(item: ArrayList<PostsBean>) {

            }

            override fun onFetchFailed() {
            }

        }.asLiveData()
    }
}