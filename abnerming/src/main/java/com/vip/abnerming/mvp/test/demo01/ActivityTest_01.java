package com.vip.abnerming.mvp.test.demo01;

import android.view.View;
import android.widget.TextView;

import com.vip.abnerming.R;
import com.vip.abnerming.mvp.presener.ActivityPresenter;

/**
 * Created by AbnerMing on 2017/12/28.
 * 未绑定数据，应用
 */

public class ActivityTest_01 extends ActivityPresenter<ActivityTestDetegate> implements View.OnClickListener {

    @Override
    public Class<ActivityTestDetegate> getDetegateClass() {
        return ActivityTestDetegate.class;
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        detegate.get(R.id.btn_click).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_click) {
           TextView textView= detegate.get(R.id.tv_test_01);
            textView.setText("动态设置数据");
        }
    }
}
