package com.example.zhangzihao.secondhand.dyx.base.impl;

/*
    create by:loser
    date:2018/12/26 1:06
*/

import android.support.v4.app.Fragment;

import com.example.zhangzihao.secondhand.dyx.base.IEventListener;
import com.example.zhangzihao.secondhand.dyx.base.IView;

public class MvpViewImpl<E extends IEventListener> extends Fragment implements IView<E> {
    private E mEventListener;

    @Override
    public void setListener(E e) {
        this.mEventListener = e;
    }

    @Override
    public E getListener() {
        return mEventListener;
    }
}
