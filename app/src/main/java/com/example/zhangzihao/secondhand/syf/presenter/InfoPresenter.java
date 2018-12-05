package com.example.zhangzihao.secondhand.syf.presenter;

import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.DataModel;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserPresenter;
import com.example.zhangzihao.secondhand.syf.model.InfoModel;
import com.example.zhangzihao.secondhand.syf.view.InfoView;

public class InfoPresenter extends UserPresenter<InfoView> {
    public void getData(String email,String session){
        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading();

        DataModel.request(InfoModel.class)
                .params(email,session)
                .execute(new UserCallback<User>(){

                    @Override
                    public void onSuccess(User data) {
                        if (isViewAttached()){
                            getMvpView().initInfo(data);
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
