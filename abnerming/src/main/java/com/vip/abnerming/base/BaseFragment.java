package com.vip.abnerming.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vip.abnerming.view.find.ViewUtils;

/**
 * Created by zhengjin on 17/8/1.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract int layoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getActivity(), layoutId(), null);
        ViewUtils.inject(this,view);
        initView(view);
        initData();
        return view;
    }
    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
