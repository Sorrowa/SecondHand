package com.example.zhangzihao.secondhand.dyx.model;

/*
    create by:loser
    date:2018/12/26 0:54
*/


import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Response;
import com.example.zhangzihao.secondhand.dyx.contract.Contract;
import com.example.zhangzihao.secondhand.dyx.model.service.ApiService;
import com.example.zhangzihao.secondhand.dyx.presenter.DealPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DealModel implements Contract.IDealModel {
    private ApiService mService;
    private Contract.IDealPresenter mPresenter;

    public DealModel(Contract.IDealPresenter presenter) {
        mService = new ApiService();
        this.mPresenter = presenter;
    }

    @Override
    public void getDealFromNet(String email) {
        mService.getDealBook(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Book> books) {
                        mPresenter.setDealBook(books);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void confirmDeal(String session, Integer bookId) {
        mService.getConfirmBook(session, bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        if (response.getCode().equals("402")) {
                            mPresenter.showSuccess(response.getMsg());
                        }
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
