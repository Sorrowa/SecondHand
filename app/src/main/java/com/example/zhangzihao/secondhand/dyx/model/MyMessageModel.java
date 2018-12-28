package com.example.zhangzihao.secondhand.dyx.model;

/*
    create by:loser
    date:2018/12/29 3:23
*/

import com.example.zhangzihao.secondhand.JavaBean.MessageBean;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpModelmpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyMessageContract;
import com.example.zhangzihao.secondhand.dyx.model.service.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyMessageModel extends MvpModelmpl implements MyMessageContract.IMyMessageModel {
    private MyMessageContract.IMyMessagePresenter mPresenter;
    private ApiService mService;

    public MyMessageModel(MyMessageContract.IMyMessagePresenter presenter) {
        this.mPresenter = presenter;
        mService = new ApiService();
    }

    @Override
    public void getMyMessage(String session, String email) {
        mService.getComments(session, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MessageBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MessageBean> messageBeans) {
                        mPresenter.setData(messageBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
