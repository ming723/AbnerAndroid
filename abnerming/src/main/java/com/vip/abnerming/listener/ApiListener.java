package com.vip.abnerming.listener;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by zhengjin on 17/8/2.
 * 所有的接口
 */

public interface ApiListener {
    @POST("LoginUser.php")
    Call<ResponseBody> getUserInFo(@Query("user_name") String userName, @Query("user_pass") String user_pass);

    @GET("week/txt/study_message.txt")
    Call<ResponseBody> getStudyList();

    @Multipart
    @POST("upload_image")
    Call<ResponseBody> upload(@Query("user_hidden") String user_hidden,
                              @Part MultipartBody.Part file);
}
