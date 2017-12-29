package com.vip.abnerming.utils.net;

import android.content.Context;

/**
 * ClassName:ApiUtil
 * Description TODO 获取ServiceApi
 * Data 2017/5/20
 * <p>
 * DownloadImageUtils.downloadLatestFeature(this,mApi,imageUrl,"download.jpg");
 * <p>
 * File file = new File(DownloadImageUtils.APP_IMAGE_DIR+"download.jpg");
 * if(file.exists()){
 * Bitmap bitmap= BitmapFactory.decodeFile(DownloadImageUtils.APP_IMAGE_DIR+"download.jpg");
 * showImg.setImageBitmap(bitmap);
 * }
 */
public class ApiUtil {

    private static AppServiceApi mApi;

    private ApiUtil() {
    }

    public static AppServiceApi getServiceApi(Context context) {
        if (mApi == null) {
            mApi = ServiceGenerator.createService(AppServiceApi.class, context);
        }
        return mApi;
    }


}
