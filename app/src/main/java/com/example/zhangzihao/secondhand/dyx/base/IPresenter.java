package com.example.zhangzihao.secondhand.dyx.base;

/*
    create by:loser
    date:2018/12/26 1:05
*/

public interface IPresenter<V extends IView, M extends IModel> {
    void setView(V view);

    void setModel(M model);

    void attach();
}
