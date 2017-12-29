package com.vip.abnerming.mvp.bind;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vip.abnerming.mvp.model.IModel;
import com.vip.abnerming.mvp.presener.ActivityPresenter;
import com.vip.abnerming.mvp.view.IDetegate;

/**
 * Created by AbnerMing on 2017/12/28.
 */

public abstract class ActivityBindData<T extends IDetegate> extends ActivityPresenter<T>{
    private DataBind dataBind;
    public abstract DataBind getDataBind();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       dataBind= getDataBind();
    }

    public <D extends IModel> void notifyChangeData(D data){
        if(dataBind!=null){
            dataBind.viewBindData(detegate,data);
        }
    }
}
