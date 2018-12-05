package com.example.zhangzihao.secondhand.syf.view;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.syf.base.UserView;

public interface LoginView extends UserView {
    void showResult(Message result,String session);
}
