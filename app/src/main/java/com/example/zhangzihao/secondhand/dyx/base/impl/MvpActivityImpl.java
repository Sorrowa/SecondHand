package com.example.zhangzihao.secondhand.dyx.base.impl;

/*
    create by:loser
    date:2018/12/27 1:24
*/



import android.app.Activity;

import com.example.zhangzihao.secondhand.dyx.base.IEventListener;
import com.example.zhangzihao.secondhand.dyx.base.IView;

public class MvpActivityImpl<E extends IEventListener>  implements IView<E> {
    private Activity mActivity;
    private E mEventListener;

    public MvpActivityImpl(Activity activity) {
        this.mActivity = activity;
    }

    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void setListener(E e) {
        this.mEventListener = e;
    }

    @Override
    public E getListener() {
        return mEventListener;
    }
}
