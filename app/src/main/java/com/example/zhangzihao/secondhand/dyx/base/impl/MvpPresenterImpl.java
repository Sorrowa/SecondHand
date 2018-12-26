package com.example.zhangzihao.secondhand.dyx.base.impl;

/*
    create by:loser
    date:2018/12/26 1:06
*/

import com.example.zhangzihao.secondhand.dyx.base.IModel;
import com.example.zhangzihao.secondhand.dyx.base.IPresenter;
import com.example.zhangzihao.secondhand.dyx.base.IView;

public abstract class MvpPresenterImpl<V extends IView, M extends IModel> implements IPresenter<V, M> {
    private V mView;
    private M mModel;


    @Override
    public void setView(V view) {
        this.mView = view;
    }

    @Override
    public void setModel(M model) {
        this.mModel = model;
    }

    public V getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }

    @Override
    public void attach() {
        onAttach();
    }

    public abstract void onAttach();
}
