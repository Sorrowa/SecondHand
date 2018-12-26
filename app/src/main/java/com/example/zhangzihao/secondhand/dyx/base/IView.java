package com.example.zhangzihao.secondhand.dyx.base;

/*
    create by:loser
    date:2018/12/26 1:05
*/

public interface IView<E extends IEventListener> {
    void setListener(E e);

    E getListener();
}
