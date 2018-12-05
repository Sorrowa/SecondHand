package com.example.zhangzihao.secondhand.zzh.View;

import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;

/**
 * (⊙o⊙)
 */
public interface BaseView <P extends BasePresenter>{

    void bindPresenter(P p);
    void detachPresenter();


}
