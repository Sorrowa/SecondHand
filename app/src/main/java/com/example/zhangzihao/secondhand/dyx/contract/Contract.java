package com.example.zhangzihao.secondhand.dyx.contract;

/*
    create by:loser
    date:2018/12/26 0:24
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.dyx.base.IModel;
import com.example.zhangzihao.secondhand.dyx.base.IPresenter;
import com.example.zhangzihao.secondhand.dyx.base.IView;
import com.example.zhangzihao.secondhand.dyx.presenter.DealEventListener;


import java.util.List;

public interface Contract {
    interface IDealModel extends IModel {
        void getDealFromNet(String email);

        void confirmDeal(String session, Integer bookId);
    }

    interface IDealView extends IView<DealEventListener> {
        void initRecyclerView(List<Book> list);

        void showSuccess(String msg);
    }

    interface IDealPresenter extends IPresenter<IDealView, IDealModel>  {
        void setDealBook(List<Book> list);

        void showSuccess(String msg);

    }
}
