package com.example.zhangzihao.secondhand.dyx.presenter;

/*
    create by:loser
    date:2018/12/27 1:27
*/

import com.example.zhangzihao.secondhand.dyx.base.IEventListener;

public interface BookEventListener extends IEventListener {
    void submitComment(String session, String email, String comment, Integer bookId);

    void getComment(Integer bookId);

    void buyBook(String session, String email, Integer bookId);
}
