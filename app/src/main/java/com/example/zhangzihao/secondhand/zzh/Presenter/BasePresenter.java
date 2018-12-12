package com.example.zhangzihao.secondhand.zzh.Presenter;


import com.example.zhangzihao.secondhand.zzh.View.BaseView;

/**
 * 实不相瞒，这里面什么都没有
 */
public interface BasePresenter <T extends BaseView>{

    void detachView();
    void bindView(T view);
}
