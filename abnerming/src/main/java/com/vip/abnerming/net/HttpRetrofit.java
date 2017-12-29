package com.vip.abnerming.net;

import retrofit2.Retrofit;

/**
 * Created by zhengjin on 17/8/2.
 */

public class HttpRetrofit {
    private HttpRetrofit() {
    }

    private static HttpRetrofit mHttpRetrofit;

    public static HttpRetrofit getmHttpRetrofit() {
        if (mHttpRetrofit == null) {
            mHttpRetrofit = new HttpRetrofit();
        }
        return mHttpRetrofit;
    }

    private Retrofit retrofit;

    public Retrofit initRetrofit(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        return retrofit;
    }
}
