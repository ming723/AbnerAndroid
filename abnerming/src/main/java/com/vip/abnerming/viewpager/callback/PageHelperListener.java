package com.vip.abnerming.viewpager.callback;

import android.view.View;

/**
 * Created by AbnerMing
 */

public interface PageHelperListener<T> {
    void getItemView(View view, T data);
}
