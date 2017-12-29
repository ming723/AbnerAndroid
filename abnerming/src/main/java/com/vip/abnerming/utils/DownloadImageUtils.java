package com.vip.abnerming.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;


import com.vip.abnerming.utils.net.AppService;
import com.vip.abnerming.utils.net.AppServiceApi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjin on 17/8/18.
 */

public class DownloadImageUtils {
    public static final String APP_IMAGE_DIR = Environment.getExternalStorageDirectory() + "/images/";

    /**
     * 下载图片到SD卡
     *
     * @param mApi
     * @param url
     * @param imageName
     */
    public static void downloadLatestFeature(final Context mContext, AppServiceApi mApi,
                                             final String url, final String imageName, final LoadImageListener listener) {
        Call<ResponseBody> resultCall = AppService.downloadLatestFeature(mApi, url);
        resultCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                writeResponseBodyToDisk(mContext, imageName, response.body(), listener);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.loadCancle();
                Logger.i("onFailure", t.getMessage());
            }
        });
    }

    /**
     * 保存下载的图片流写入SD卡文件
     *
     * @param imageName xxx.jpg
     * @param body      image stream
     */
    public static void writeResponseBodyToDisk(Context mContext, String imageName, ResponseBody body, LoadImageListener listener) {
        if (body == null) {
            Toast.makeText(mContext, "图片源错误", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            InputStream is = body.byteStream();
            File fileDr = new File(APP_IMAGE_DIR);
            if (!fileDr.exists()) {
                fileDr.mkdir();
            }
            File file = new File(APP_IMAGE_DIR, imageName);
            if (file.exists()) {
                file.delete();
                file = new File(APP_IMAGE_DIR, imageName);
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            fos.flush();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listener.loadOk();
        }
    }

    public interface LoadImageListener {
        void loadOk();

        void loadCancle();
    }
}
