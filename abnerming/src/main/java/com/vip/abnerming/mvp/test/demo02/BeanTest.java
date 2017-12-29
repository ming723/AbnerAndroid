package com.vip.abnerming.mvp.test.demo02;

import com.vip.abnerming.mvp.model.IModel;

/**
 * Created by Administrator on 2017/12/28.
 */

public class BeanTest implements IModel{

    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
