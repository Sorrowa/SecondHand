package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/26 0:54
*/

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpPresenterImpl;
import com.example.zhangzihao.secondhand.dyx.contract.Contract;


import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DealPresenter extends MvpPresenterImpl<Contract.IDealView, Contract.IDealModel> implements Contract.IDealPresenter {
    private Contract.IDealView mView;
    private Contract.IDealModel mModel;

    private DealEventListener mListener = new DealEventListener() {
        @Override
        public void loadDealBook(String email) {
            mModel.getDealFromNet(email);
        }

        @Override
        public void confirmDeal(String session, Integer bookId) {
            mModel.confirmDeal(session, bookId);
        }
    };

    @Override
    public void setDealBook(List<Book> list) {
        mView.initRecyclerView(list);
    }

    @Override
    public void showSuccess(String msg) {
        mView.showSuccess(msg);
    }


    @Override
    public void onAttach() {
        mView = getView();
        mModel = getModel();

        mView.setListener(mListener);
    }
}
