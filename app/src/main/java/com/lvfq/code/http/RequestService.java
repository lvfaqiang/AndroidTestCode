package com.lvfq.code.http;

import com.lvfq.code.ModelBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    /**
     * 测试文件上传
     *
     * @param value
     * @param file
     * @return
     */
    @Multipart
    @POST("Synthesis/uploadLog")
    Call<String> uploadFile(@Part("test") String value, @Part MultipartBody.Part file);
}
