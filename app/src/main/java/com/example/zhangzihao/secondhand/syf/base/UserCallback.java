package com.example.zhangzihao.secondhand.syf.base;

public interface UserCallback<T> {
    void onSuccess(T data);
    void onFailure(String msg);
    void onError();
    void onComplete();
}
