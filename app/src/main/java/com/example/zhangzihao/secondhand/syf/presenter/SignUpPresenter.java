package com.example.zhangzihao.secondhand.syf.presenter;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.DataModel;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserPresenter;
import com.example.zhangzihao.secondhand.syf.model.SignUpModel;
import com.example.zhangzihao.secondhand.syf.view.SignUpView;

public class SignUpPresenter extends UserPresenter<SignUpView> {
    private SignUpModel signUpModel;
    public void commitData(User user){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();

        signUpModel = new SignUpModel(user);
        signUpModel.execute(new UserCallback<Message>(){
            @Override
            public void onSuccess(Message data) {
                if (isViewAttached()){
                    getMvpView().showResult(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()){
                    getMvpView().showToast(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()){
                    getMvpView().showErr();
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()){
                    getMvpView().hideLoading();
                }
            }
        });
    }
}
