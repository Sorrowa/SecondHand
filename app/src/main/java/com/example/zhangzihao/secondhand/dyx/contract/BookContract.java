package com.example.zhangzihao.secondhand.dyx.contract;

/*
    create by:loser
    date:2018/12/27 1:28
*/

import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.dyx.base.IModel;
import com.example.zhangzihao.secondhand.dyx.base.IPresenter;
import com.example.zhangzihao.secondhand.dyx.base.IView;
import com.example.zhangzihao.secondhand.dyx.presenter.BookEventListener;

import java.util.List;

public interface BookContract {
    interface IBookModel extends IModel {
        void getComment(Integer bookId);

        void insertComment(String session, String email, String comment, Integer bookId);

        void buyBook(String session, String email, Integer bookId);
    }

    interface IBookView extends IView<BookEventListener> {
        void initComment(List<Data.CommentsBean> comments);

        void refreshComment();

        void showSuccess(String msg);
    }

    interface IBookPresenter extends IPresenter<IBookView, IBookModel> {
        void initComment(List<Data.CommentsBean> comments);

        void refreshComment();

        void showSuccess(String msg);
    }
}
