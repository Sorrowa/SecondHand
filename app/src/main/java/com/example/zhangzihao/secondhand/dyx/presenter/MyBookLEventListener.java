package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/29 0:39
*/

import com.example.zhangzihao.secondhand.dyx.base.IEventListener;

public interface MyBookLEventListener extends IEventListener {
    void getMyBook(String session, String email);

    void changeBook(String session, String email, Integer bookId, Integer changeId);
}
