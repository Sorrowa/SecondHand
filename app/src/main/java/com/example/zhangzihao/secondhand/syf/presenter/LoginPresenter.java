package com.example.zhangzihao.secondhand.syf.presenter;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.syf.base.DataModel;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserPresenter;
import com.example.zhangzihao.secondhand.syf.fragment.LoginCallback;
import com.example.zhangzihao.secondhand.syf.model.LoginModel;
import com.example.zhangzihao.secondhand.syf.view.LoginView;

public class LoginPresenter extends UserPresenter<LoginView> {
    private LoginModel loginModel;
    public void getData(String email,String pwd){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();

        loginModel = new LoginModel(email,pwd);
        loginModel.execute(new LoginCallback<Message>() {
            @Override
            public void loginSuccess(Message data, String session) {
                if (isViewAttached()){
                    getMvpView().showResult(data,session);
                }
            }


            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()){
                    getMvpView().showErr();
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

    public void resetPwd(String email){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();
        DataModel.request(LoginModel.class)
                .params(email)
                .execute(new UserCallback<Message>() {
                    @Override
                    public void onSuccess(Message msg) {
                        if (isViewAttached()){
                            getMvpView().showResult(msg);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        if (isViewAttached()){
                            getMvpView().showErr();
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
