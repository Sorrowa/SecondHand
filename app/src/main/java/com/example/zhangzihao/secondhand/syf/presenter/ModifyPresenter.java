package com.example.zhangzihao.secondhand.syf.presenter;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserPresenter;
import com.example.zhangzihao.secondhand.syf.model.ModifyModel;
import com.example.zhangzihao.secondhand.syf.view.ModifyView;

import java.io.File;

public class ModifyPresenter extends UserPresenter<ModifyView>{
    private ModifyModel modifyModel;
    public void commitModify(User user){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();
        modifyModel = new ModifyModel(user);
        modifyModel.params("postUser");
        modifyModel.execute(new UserCallback<Message>() {
            @Override
            public void onSuccess(Message data) {
                if (isViewAttached()){
                    getMvpView().showResult(data);
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

    public void getUser(String email){
        if (!isViewAttached()){
            return ;
        }
        //getMvpView().showLoading();
        modifyModel = new ModifyModel(email);
        modifyModel.params("requestUser");
        modifyModel.execute(new UserCallback<User>() {
            @Override
            public void onSuccess(User data) {
                if (isViewAttached()){
                    getMvpView().modifyUI(data);
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

    public void change_head(File file,String email){
        if (!isViewAttached()){
            return ;
        }
        modifyModel = new ModifyModel(file,email);
        modifyModel.params("changeHead");
        modifyModel.execute(new UserCallback<Message>() {
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
