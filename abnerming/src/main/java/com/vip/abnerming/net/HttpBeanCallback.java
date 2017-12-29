package com.vip.abnerming.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/26.
 * 返回JavaBean
 */

public abstract class HttpBeanCallback implements Callback<ResponseBody> {
    private Class aClass;
    public HttpBeanCallback(Class cls){
        aClass=cls;
    }
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            String mess = response.body().string();
            Object bean = new Gson().fromJson(mess, aClass);
            onSuccess(bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onFailure();
    }

    protected abstract void onSuccess( Object bean);
    protected abstract void onFailure( );
}
