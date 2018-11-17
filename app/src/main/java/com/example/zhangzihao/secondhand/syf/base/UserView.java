package com.example.zhangzihao.secondhand.syf.base;

import android.content.Context;

public interface UserView {
    void showLoading();
    void hideLoading();
    void showToast(String msg);
    void showErr();
    Context getContext();
}
