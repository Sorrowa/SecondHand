package com.example.zhangzihao.secondhand.dyx.contract;

/*
    create by:loser
    date:2018/12/29 3:23
*/

import com.example.zhangzihao.secondhand.JavaBean.MessageBean;
import com.example.zhangzihao.secondhand.dyx.base.IModel;
import com.example.zhangzihao.secondhand.dyx.base.IPresenter;
import com.example.zhangzihao.secondhand.dyx.base.IView;
import com.example.zhangzihao.secondhand.dyx.presenter.MyMessageEventListener;

import java.util.List;

public interface MyMessageContract {

    interface IMyMessageView extends IView<MyMessageEventListener> {
        void setData(List<MessageBean> books);

        void getMyMessage();
    }

    interface IMyMessagePresenter extends IPresenter<IMyMessageView, IMyMessageModel> {
        void setData(List<MessageBean> books);
    }

    interface IMyMessageModel extends IModel {
        void getMyMessage(String session, String email);
    }
}
