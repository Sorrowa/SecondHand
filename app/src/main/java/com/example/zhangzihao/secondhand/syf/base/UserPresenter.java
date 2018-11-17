package com.example.zhangzihao.secondhand.syf.base;

public class UserPresenter<V extends UserView> {
    private V userView;

    public void attachView(V mvpView){
        this.userView = mvpView;
    }

    public void detachView(){
        this.userView = null;
    }

    public boolean isViewAttached(){
        return userView != null;
    }

    public V getMvpView(){
        return userView;
    }
}
