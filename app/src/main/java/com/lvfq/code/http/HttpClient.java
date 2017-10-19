package com.lvfq.code.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * HttpClient
 *
 * @author lvfq
 * @date 2017/7/13 下午11:00
 * @mainFunction :
 */

public class HttpClient {

    private static RequestService service;
    private static long TIMOUT = 10000; //设置超时时间
//    http://an.zdeps.com/Invoking/user_info?keys=64b83c8381fc6d059ecd352f776124be&vci=12121212121H

    /**
     * 调用 retrofit 服务
     *
     * @return
     */
    public static RequestService getService() {
        if (service == null) {
            synchronized (HttpClient.class) {
                if (service == null) {
                    service = new Retrofit.Builder()
//                            .baseUrl("http://192.168.0.107:8080/")
                            .baseUrl("http://an.zdeps.com/index.php/")
                            .addConverterFactory(NobodyConverterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .client(new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
//                                @Override
//                                public Response intercept(Chain chain) throws IOException {
//                                    Request request = chain.request();
//                                    HttpUrl url = request.url().newBuilder().addQueryParameter("platformType", "ANDROID").addQueryParameter("version", V.getVersionName(MyApplication.getContext())).build();
//                                    return chain.proceed(request.newBuilder().url(url).build());
//                                }
//                            }).build())
                            .client(new OkHttpClient().newBuilder().connectTimeout(TIMOUT, TimeUnit.MILLISECONDS)
                                    .writeTimeout(TIMOUT, TimeUnit.MILLISECONDS)
                                    .readTimeout(TIMOUT, TimeUnit.MILLISECONDS)
                                    .build())
                            .build()
                            .create(RequestService.class);
                }
            }
        }
        return service;
    }

}