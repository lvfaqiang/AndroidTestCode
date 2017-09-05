package com.lvfq.code.http;

import com.lvfq.code.ModelBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * RequestService
 *
 * @author lvfq
 * @date 2017/7/13 下午11:00
 * @mainFunction :
 */

public interface RequestService {

    @POST("login")
    Call<ModelBean> login(@Query("userName") String userName,
                          @Query("passWord") String passWord);

    @FormUrlEncoded
    @POST("Invoking/user_info")
    Call<ModelBean> userInfo(@Field("keys") String keys,
                             @Field("vci") String vci);

    @GET("front/server")
    Call<ModelBean> serverInfo();
}
