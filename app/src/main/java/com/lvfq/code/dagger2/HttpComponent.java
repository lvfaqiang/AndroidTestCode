package com.lvfq.code.dagger2;

import okhttp3.OkHttpClient;

/**
 * HttpComponent
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/12/9 下午12:57
 * @desc :
 */
@dagger.Component(modules = HttpModule.class)
public interface HttpComponent {
    // 这里需要对外暴露出获取 OkHttpClient 的方法，方法名不需要和 HttpModule 中保持一致。
    OkHttpClient provideOkHttp();
}
