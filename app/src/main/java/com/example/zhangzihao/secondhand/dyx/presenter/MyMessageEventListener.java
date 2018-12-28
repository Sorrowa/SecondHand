package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/29 3:23
*/

import com.example.zhangzihao.secondhand.dyx.base.IEventListener;

public interface MyMessageEventListener extends IEventListener {
    void getMyMessage(String session, String email);
}
