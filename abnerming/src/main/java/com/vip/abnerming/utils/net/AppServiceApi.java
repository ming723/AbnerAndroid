package com.vip.abnerming.utils.net;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * ClassName:AppServiceApi
 * Description TODO 接口
 * created by BAI
 * Data 2016/12/20 11:36
 */

public interface AppServiceApi {

    /**
     * 下载图片
     *
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadLatestFeature(@Url String fileUrl);

}
