package com.vip.abnerming.bean;

/**
 * Created by zhengjin on 17/8/15.
 * 所有实体类的父类
 */

public class BaseBean {
    private int status;
    private int state;
    private String msg;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
