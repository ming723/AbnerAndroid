package com.vip.abnerming.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/26.
 * 返回字符串
 */

public abstract class HttpCallback implements Callback<ResponseBody> {
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            String mess = response.body().string();
            onSuccess(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onFailure();
    }

    protected abstract void onSuccess( String mess);
    protected abstract void onFailure( );
}
