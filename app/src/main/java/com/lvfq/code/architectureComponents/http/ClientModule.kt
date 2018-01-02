package com.lvfq.code.architectureComponents.http

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * ClientModule
 * @author FaQiang on 2017/12/22 上午10:57
 * @Github: <a href="https://github.com/lvfaqiang">
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
@Module(includes = [ClientConfig::class])
class ClientModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(builder: Retrofit.Builder, url: HttpUrl, client: OkHttpClient): Retrofit {

        return builder.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }


    /**
     * init OkhttpClient
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder, interceptors: ArrayList<Interceptor>): OkHttpClient {

        interceptors.map { builder.interceptors().add(it) }

        return builder.build()
    }


    /**
     * init RetrofitBuilder
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }
}