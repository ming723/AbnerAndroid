package com.vip.abnerming.mvp.test.demo01;

import android.widget.TextView;

import com.vip.abnerming.R;
import com.vip.abnerming.mvp.view.AppIDetegate;

/**
 * Created by AbnerMing on 2017/12/28.
 */

public class ActivityTestDetegate extends AppIDetegate{

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_01;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        //设置数据
        TextView textView=get(R.id.tv_test_01);
        textView.setText("AbnerMing,Hello!!!!!!");
    }

    //动态设置数据
    public void setTextMessage(String msg){
        TextView textView=get(R.id.tv_test_01);
        textView.setText(msg);
    }
}
