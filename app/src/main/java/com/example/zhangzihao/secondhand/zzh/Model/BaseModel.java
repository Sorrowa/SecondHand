package com.example.zhangzihao.secondhand.zzh.Model;

import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;

/**
 * 这里什么都没有(⊙o⊙)
 */
public interface BaseModel<T extends BasePresenter> {
    void bindPresenter(T p);
    void detachPresenter();
}
