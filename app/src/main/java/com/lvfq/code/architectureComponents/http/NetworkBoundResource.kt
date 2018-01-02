/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lvfq.code.architectureComponents.http

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import android.text.TextUtils
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * 可以提供由sqlite数据库和网络支持的资源的通用类
 *
 * @param <ResultType>
 * *
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor() {

    private val result = MediatorLiveData<ResultType>()

    init {
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {

        val requestObservable: Observable<RequestType> = createCall()

        requestObservable
                .subscribeOn(Schedulers.io())
                .flatMap { it ->
                    val rt: ResultType? = processResponse(it)
                    if (null == rt) {
                        Observable.empty<ResultType>()
                    } else {
                        // 执行保存数据操作
                        saveCallResult(rt)

                        Observable.just(rt)
                    }

                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResultType>() {

                    override fun onComplete() {
                        onFinally()
                    }

                    override fun onNext(res: ResultType?) {
                        result.value = res
                    }

                    override fun onError(t: Throwable?) {
                        if (t != null && !TextUtils.isEmpty(t.message)) {
                            Log.d("onError", t.message)
                        }

                        onFetchFailed()
                        onFinally()
                    }
                })
    }

    fun asLiveData(): LiveData<ResultType> {
        return result
    }

    /**
     * 请求结束时执行；不管正常结束还是出错，总会执行一次
     */
    protected open fun onFinally() {

    }

    /**
     * 创建网络请求
     */
    @MainThread
    protected abstract fun createCall(): Observable<RequestType>

    /**
     * 将RequestType转换为ResultType
     */
    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType?


    /**
     * 将数据保存到数据库
     */
    @WorkerThread
    protected open fun saveCallResult(item: ResultType) {
    }

    /**
     * 请求错误处理
     */
    @MainThread
    protected abstract fun onFetchFailed()
}
