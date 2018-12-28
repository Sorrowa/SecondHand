package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/29 0:39
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpPresenterImpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyBookContract;
import com.example.zhangzihao.secondhand.dyx.model.MyBookModel;
import com.example.zhangzihao.secondhand.dyx.view.MyBookView;

import java.util.List;

public class MyBookPresenter extends MvpPresenterImpl<MyBookContract.IMyBookView, MyBookContract.IMyBookModel> implements MyBookContract.IMyBookPresenter {
    private MyBookContract.IMyBookView mView;
    private MyBookContract.IMyBookModel mModel;

    private MyBookLEventListener mListener = new MyBookLEventListener() {
        @Override
        public void getMyBook(String session, String email) {
            mModel.getMyBook(session, email);
        }

        @Override
        public void changeBook(String session, String email, Integer bookId, Integer changeId) {
            mModel.changeBook(session, email, bookId, changeId);
        }
    };

    @Override
    public void onAttach() {
        mView = getView();
        mModel = getModel();

        mView.setListener(mListener);

        mView.getMyBook();
    }


    @Override
    public void setData(List<Book> books) {
        mView.setData(books);
    }

    @Override
    public void showSuccess(String msg) {
        mView.showSuccess(msg);
    }

    @Override
    public void showError(String msg) {
        mView.showError(msg);
    }
}
