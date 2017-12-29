package com.vip.abnerming;

import android.content.Context;

import com.vip.abnerming.listener.ApiListener;
import com.vip.abnerming.net.HttpCallback;
import com.vip.abnerming.net.HttpHelper;

/**
 * Created by Administrator on 2017/12/26.
 */

public class Test {
    private  ApiListener apiListener;
    public Test(){
       apiListener= HttpHelper.getApiListener();
    }
    public void getUserInFo(final Context ctx, String name, String pass, final HttpHelper.UserLoginListener listener) {
        apiListener.getUserInFo(name, pass).enqueue(new HttpCallback(){

            @Override
            protected void onSuccess(String mess) {
                listener.loginOk();
            }

            @Override
            protected void onFailure() {
                listener.cancle();
            }
        });
    }

    public interface UserLoginListener {
        void loginOk();

        void cancle();
    }

}
