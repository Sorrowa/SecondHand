package com.example.zhangzihao.secondhand.dyx.model;

/*
    create by:loser
    date:2018/12/29 0:39
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Response;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpModelmpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyBookContract;
import com.example.zhangzihao.secondhand.dyx.model.service.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyBookModel extends MvpModelmpl implements MyBookContract.IMyBookModel {
    private MyBookContract.IMyBookPresenter mPresenter;
    private ApiService mService;

    public MyBookModel(MyBookContract.IMyBookPresenter presenter) {
        this.mPresenter = presenter;
        mService = new ApiService();
    }

    @Override
    public void getMyBook(String session, String email) {
        mService.getMyBook(session, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Book> books) {
                        mPresenter.setData(books);
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
    public void changeBook(String session, String email, Integer bookId, Integer changeId) {
        mService.changeBook(session, email, bookId, changeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        if (response.getCode().equals("401")) {
                            mPresenter.showSuccess(response.getMsg());
                        } else {
                            mPresenter.showError("交换失败，请重试...");
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
