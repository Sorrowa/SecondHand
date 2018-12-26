package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/26 0:54
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpPresenterImpl;
import com.example.zhangzihao.secondhand.dyx.contract.Contract;


import java.util.ArrayList;

public class DealPresenter extends MvpPresenterImpl<Contract.IDealView, Contract.IDealModel> implements Contract.IDealPresenter {
    private Contract.IDealView mView;
    private Contract.IDealModel mModel;

    private DealEventListener mListener = new DealEventListener() {
        @Override
        public void loadDealBook() {
            mModel.getDealFromNet();
        }
    };

    @Override
    public void setDealBook(ArrayList<Book> list) {
        mView.initRecyclerView(list);
    }


    @Override
    public void onAttach() {
        mView = getView();
        mModel = getModel();

        mView.setListener(mListener);
    }
}
