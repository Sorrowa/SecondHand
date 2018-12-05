package com.example.zhangzihao.secondhand.syf.fragment;

import com.example.zhangzihao.secondhand.syf.base.UserCallback;

public interface LoginCallback<T> extends UserCallback {
    void loginSuccess(T data,String session);
}
