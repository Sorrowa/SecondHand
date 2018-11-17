package com.example.zhangzihao.secondhand.syf.presenter;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.syf.base.DataModel;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserPresenter;
import com.example.zhangzihao.secondhand.syf.model.LoginModel;
import com.example.zhangzihao.secondhand.syf.view.LoginView;

public class LoginPresenter extends UserPresenter<LoginView> {
    public void getData(String email,String pwd){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();

        DataModel.request(LoginModel.class)
                .params(email,pwd)
                .execute(new UserCallback<Message>() {
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
