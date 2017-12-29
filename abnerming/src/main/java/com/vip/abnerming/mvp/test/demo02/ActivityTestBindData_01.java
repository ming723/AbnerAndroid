package com.vip.abnerming.mvp.test.demo02;

import android.view.View;

import com.vip.abnerming.R;
import com.vip.abnerming.mvp.bind.ActivityBindData;
import com.vip.abnerming.mvp.bind.DataBind;

/**
 * Created by AbnerMing on 2017/12/28.
 * 采取双向绑定
 */

public class ActivityTestBindData_01 extends ActivityBindData<ActivityTestDetegate> implements View.OnClickListener {

    @Override
    public DataBind getDataBind() {
        return new ActivityTestBind();
    }

    @Override
    public Class<ActivityTestDetegate> getDetegateClass() {
        return ActivityTestDetegate.class;
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        detegate.setOnClickListener(this,R.id.btn_click);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_click) {
            BeanTest beanTest=new BeanTest();
            beanTest.setMess("我是AbnerMing");
            notifyChangeData(beanTest);
        }
    }
}
