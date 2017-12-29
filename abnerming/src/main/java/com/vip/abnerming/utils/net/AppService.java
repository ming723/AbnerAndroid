package com.vip.abnerming.utils.net;



import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * ClassName: AppService
 * Description TODO 调用接口
 * Created by BAI
 * Data 2017/5/7.
 */

public class AppService {

    /**
     * 下载最新模板图片
     * @param api
     */
    public static Call<ResponseBody> downloadLatestFeature(AppServiceApi api, String imageUrl) {
        return api.downloadLatestFeature(imageUrl);
    }



}
