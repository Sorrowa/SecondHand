package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/27 1:27
*/

import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpPresenterImpl;
import com.example.zhangzihao.secondhand.dyx.contract.BookContract;

import java.util.List;

public class BookPresenter extends MvpPresenterImpl<BookContract.IBookView, BookContract.IBookModel> implements BookContract.IBookPresenter {
    private BookContract.IBookView mView;
    private BookContract.IBookModel mModel;

    private BookEventListener mListener = new BookEventListener() {
        @Override
        public void submitComment(String session, String email, String comment, Integer bookId) {
            mModel.insertComment(session, email, comment, bookId);
        }

        @Override
        public void getComment(Integer bookId) {
            mModel.getComment(bookId);
        }

        @Override
        public void buyBook(String session, String email, Integer bookId) {
            mModel.buyBook(session, email, bookId);
        }
    };

    @Override
    public void onAttach() {
        this.mView = getView();
        this.mModel = getModel();

        this.mView.setListener(mListener);

        mView.refreshComment();
    }

    @Override
    public void initComment(List<Data.CommentsBean> comments) {
        mView.initComment(comments);
    }

    @Override
    public void refreshComment() {
        mView.refreshComment();
    }

    @Override
    public void showSuccess(String msg) {
        mView.showSuccess(msg);
    }
}
