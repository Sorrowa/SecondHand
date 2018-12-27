package com.example.zhangzihao.secondhand.dyx.model;

/*
    create by:loser
    date:2018/12/27 1:27
*/

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.JavaBean.Response;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpModelmpl;
import com.example.zhangzihao.secondhand.dyx.contract.BookContract;
import com.example.zhangzihao.secondhand.dyx.model.service.ApiService;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class BookModel extends MvpModelmpl implements BookContract.IBookModel {
    private BookContract.IBookPresenter mPresenter;
    private ApiService mService;

    public BookModel(BookContract.IBookPresenter presenter) {
        this.mPresenter = presenter;
        mService = new ApiService();
    }

    @Override
    public void getComment(Integer bookId) {
        mService.getBook(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data book) {
                        if (book != null) {
                            mPresenter.initComment(book.getComments());
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

    @Override
    public void insertComment(String session, String email, String comment, Integer bookId) {
        Comment c = new Comment();
        c.setWriterEmail(email);
        c.setContent(comment);
        c.setBookId(bookId);

        Gson gson = new Gson();
        final String insert = gson.toJson(c);

        mService.insertComment(session, insert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer != 0) {
                            mPresenter.refreshComment();
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

    @Override
    public void buyBook(String session, String email, Integer bookId) {
        mService.buyBook(session, email, bookId)
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
