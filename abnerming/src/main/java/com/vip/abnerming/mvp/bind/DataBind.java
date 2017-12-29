package com.vip.abnerming.mvp.bind;

import com.vip.abnerming.mvp.model.IModel;
import com.vip.abnerming.mvp.view.IDetegate;

/**
 * Created by AbnerMing on 2017/12/28.
 * 绑定数据
 */

public interface DataBind<T extends IDetegate,D extends IModel> {

    void viewBindData(T t,D d);
}
