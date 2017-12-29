package com.vip.abnerming.mvp.presener;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vip.abnerming.mvp.view.IDetegate;

/**
 * Created by AbnerMing on 2017/12/28.
 */

public abstract class ActivityPresenter<T extends IDetegate> extends AppCompatActivity {

    public T detegate;

    public ActivityPresenter() {
        try {
            detegate = getDetegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public abstract Class<T> getDetegateClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(detegate.getRootView());
        detegate.initWeight();
        bindEnvsListener();
    }

    public void bindEnvsListener() {

    }
}
