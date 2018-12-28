package com.example.zhangzihao.secondhand.dyx.contract;

/*
    create by:loser
    date:2018/12/29 0:40
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.dyx.base.IModel;
import com.example.zhangzihao.secondhand.dyx.base.IPresenter;
import com.example.zhangzihao.secondhand.dyx.base.IView;
import com.example.zhangzihao.secondhand.dyx.presenter.MyBookLEventListener;

import java.util.List;

public interface MyBookContract {

    interface IMyBookView extends IView<MyBookLEventListener> {
        void setData(List<Book> books);

        void getMyBook();

        void showSuccess(String msg);

        void showError(String msg);
    }

    interface IMyBookPresenter extends IPresenter<IMyBookView, IMyBookModel> {
        void setData(List<Book> books);

        void showSuccess(String msg);

        void showError(String msg);
    }

    interface IMyBookModel extends IModel {
        void getMyBook(String session, String email);

        void changeBook(String session, String email, Integer bookId, Integer changeId);
    }

}
