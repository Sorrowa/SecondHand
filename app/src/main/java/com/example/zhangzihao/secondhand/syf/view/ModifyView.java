package com.example.zhangzihao.secondhand.syf.view;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserView;

public interface ModifyView extends UserView {
    void modifyUI(User user);
    void showResult(Message msg);
}
