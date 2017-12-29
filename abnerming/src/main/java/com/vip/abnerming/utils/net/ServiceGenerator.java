package com.vip.abnerming.utils.net;

import android.content.Context;
import android.util.Log;


import com.vip.abnerming.utils.net.gson.MGsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * ClassName:ServiceGenerator
 * Description TODO API 生成
 * created by BAI
 * Data 2016/12/20
 */
public class ServiceGenerator {
    // 使用OkHttp发起网络请求
    private static OkHttpClient httpClient;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://www.weekandroid.cn/")
            .addConverterFactory(MGsonConverterFactory.create());


    /**
     * 创建service
     *
     * @param sreviceClass
     * @param context
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> sreviceClass, final Context context) {
        //缓存路径
        File cacheFile = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        //缓存大小
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //缓存文件为100MB
        //可以查看网络请求的日志
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                try {
                    //String result = AESOperator.getInstance().decrypt(message);
                    Log.i("LoggingInterceptor", message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //设置日志level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = null;
        long DEFAULT_TIMEOUT = 100000;//超时时间10秒

        //okHttp添加拦截器，带有cookie拦截
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache).build();

        retrofit = builder.client(httpClient).build();
        return retrofit.create(sreviceClass);
    }


}
