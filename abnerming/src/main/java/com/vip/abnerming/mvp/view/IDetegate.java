package com.vip.abnerming.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AbnerMing on 2017/12/28.
 */

public interface IDetegate {

    void initWeight();//初始化组件信息

    View getRootView();//获取View信息

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

}
