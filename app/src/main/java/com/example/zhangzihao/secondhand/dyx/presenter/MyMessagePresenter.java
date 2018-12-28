package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/29 3:23
*/

import com.example.zhangzihao.secondhand.JavaBean.MessageBean;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpPresenterImpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyMessageContract;

import java.util.List;

public class MyMessagePresenter extends MvpPresenterImpl<MyMessageContract.IMyMessageView, MyMessageContract.IMyMessageModel> implements MyMessageContract.IMyMessagePresenter {
    private MyMessageContract.IMyMessageView mView;
    private MyMessageContract.IMyMessageModel mModel;

    private MyMessageEventListener mListener = new MyMessageEventListener() {
        @Override
        public void getMyMessage(String session, String email) {
            mModel.getMyMessage(session, email);
        }
    };

    @Override
    public void onAttach() {
        mView = getView();
        mModel = getModel();

        mView.setListener(mListener);

        mView.getMyMessage();
    }

    @Override
    public void setData(List<MessageBean> books) {
        mView.setData(books);
    }
}
