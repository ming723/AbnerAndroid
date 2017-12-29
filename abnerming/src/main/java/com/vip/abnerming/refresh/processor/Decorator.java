package com.vip.abnerming.refresh.processor;

import com.vip.abnerming.refresh.TwinklingRefreshLayout;

/**
 * Created by lcodecore on 2017/3/3.
 */

public abstract class Decorator implements IDecorator {
    protected IDecorator decorator;
    protected TwinklingRefreshLayout.CoContext cp;

    public Decorator(TwinklingRefreshLayout.CoContext processor, IDecorator decorator1) {
        cp = processor;
        decorator = decorator1;
    }
}
