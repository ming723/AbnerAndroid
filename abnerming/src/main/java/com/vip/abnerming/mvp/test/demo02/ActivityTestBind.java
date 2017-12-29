package com.vip.abnerming.mvp.test.demo02;

import com.vip.abnerming.R;
import com.vip.abnerming.mvp.bind.ActivityBindData;
import com.vip.abnerming.mvp.bind.DataBind;

/**
 * Created by Administrator on 2017/12/28.
 */

public class ActivityTestBind implements DataBind<ActivityTestDetegate,BeanTest>{
    @Override
    public void viewBindData(ActivityTestDetegate activityTestDetegate, BeanTest beanTest) {
        activityTestDetegate.setTextMessage(beanTest.getMess());
    }
}
