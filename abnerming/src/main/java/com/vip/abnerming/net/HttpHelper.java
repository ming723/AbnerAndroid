package com.vip.abnerming.net;

import android.content.Context;

import com.vip.abnerming.api.BaseApi;
import com.vip.abnerming.listener.ApiListener;

import retrofit2.Retrofit;

/**
 * Created by AbnerMing
 */

public class HttpHelper {
    private static String TAG = HttpHelper.class.getName();
    private static ApiListener listenerApi=null;
    private HttpHelper() {

    }
    public static ApiListener getApiListener(){
        if(listenerApi==null){
            Retrofit retrofit = HttpRetrofit.getmHttpRetrofit().initRetrofit(BaseApi.HTTP_URL);
            listenerApi = retrofit.create(ApiListener.class);
        }
        return listenerApi;
    }

    /**
     * 具体运用
     * */
    public void getUserInFo(final Context ctx, String name, String pass, final UserLoginListener listener) {
        listenerApi.getUserInFo(name, pass).enqueue(new HttpCallback(){

            @Override
            protected void onSuccess(String mess) {

            }

            @Override
            protected void onFailure() {

            }
        });
    }

    public interface UserLoginListener {
        void loginOk();

        void cancle();
    }
}
