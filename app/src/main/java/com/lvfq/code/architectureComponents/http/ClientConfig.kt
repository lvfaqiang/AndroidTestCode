package com.lvfq.code.architectureComponents.http

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * ClientConfig
 * @author FaQiang on 2017/12/22 下午12:48
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Module
class ClientConfig {

    private val TIME_OUT = 10L  // 源码内部默认也是 10s

    /**
     * init OkHttpClientBuilder
     */
    @Singleton
    @Provides
    fun provideClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
    }


    /**
     * 生成 HttpUrl 用于 Retfofit 添加
     */
    @Singleton
    @Provides
    fun getHttpUrl(): HttpUrl {
        return HttpUrl.parse("http://i.jandan.net/") ?: throw IllegalAccessException(" BuildConfig.HttpUrl is Null, Configure HttpUrl in app build.gradle please")
    }

    /**
     * 添加过滤器
     */
    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = ArrayList<Interceptor>()

        interceptors.add(Interceptor { c ->
            // 添加公共参数
            val request = c.request()
            val builder = request.url().newBuilder()

//            builder.addQueryParameter("key",value)

            return@Interceptor c.proceed(request.newBuilder().url(builder.build()).build())
        })

        return interceptors
    }

}