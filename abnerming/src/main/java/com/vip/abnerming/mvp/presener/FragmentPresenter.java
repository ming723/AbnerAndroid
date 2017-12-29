package com.vip.abnerming.mvp.presener;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vip.abnerming.mvp.view.IDetegate;

/**
 * Created by Administrator on 2017/12/28.
 * mvp Fragment
 */

public abstract class FragmentPresenter<T extends IDetegate>  extends Fragment{

    public T datagale;

    public abstract Class<T> geDetegaleClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            datagale=geDetegaleClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        datagale.create(inflater,container,savedInstanceState);
        return datagale.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datagale.initWeight();
        bindEnvsListener();
    }

    public void bindEnvsListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        datagale=null;
    }
}
