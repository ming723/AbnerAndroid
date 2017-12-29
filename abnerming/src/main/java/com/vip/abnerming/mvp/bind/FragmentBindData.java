package com.vip.abnerming.mvp.bind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vip.abnerming.mvp.model.IModel;
import com.vip.abnerming.mvp.presener.FragmentPresenter;
import com.vip.abnerming.mvp.view.IDetegate;

/**
 * Created by Administrator on 2017/12/28.
 */

public abstract class FragmentBindData <T extends IDetegate> extends FragmentPresenter<T>{

    private DataBind dataBind;

    public abstract DataBind getDataBind();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBind=getDataBind();
    }

    public <D extends IModel> void notifyChangeData(D data){
        if(dataBind!=null){
            dataBind.viewBindData(datagale,data);
        }
    }
}
