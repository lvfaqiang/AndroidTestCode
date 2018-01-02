package com.lvfq.code.architectureComponents.http

import com.lvfq.code.architectureComponents.bean.FreshNewsBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url


/**
 * ApiService
 * @author FaQiang on 2017/12/27 上午11:07
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
interface ApiService {

    @GET
    fun getFreshNews(@Url url: String,
                     @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): Observable<FreshNewsBean>


//    @GET
//    fun getDetailData(@Url url: String, @Query("oxwlxojflwblxbsapi") oxwlxojflwblxbsapi: String,
//                      @Query("page") page: Int
//    ): Observable<JdDetailBean>
//
//    @GET
//    fun getFreshNewsArticle(@Url url: String, @Query("oxwlxojflwblxbsapi") oxwlxojflwblxbsapi: String,
//                            @Query("include") include: String,
//                            @Query("id") id: Int
//    ): Observable<FreshNewsArticleBean>
}