package com.example.zhangzihao.secondhand.zzh.Model;

import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;

public class PublishModel implements BaseModel<PublishBookPresenter>{


    private PublishBookPresenter p;

    public PublishModel(PublishBookPresenter p){
        bindPresenter(p);
    }

    @Override
    public void bindPresenter(PublishBookPresenter p) {
        this.p=p;
    }

    @Override
    public void detachPresenter() {
        p=null;
    }
}
