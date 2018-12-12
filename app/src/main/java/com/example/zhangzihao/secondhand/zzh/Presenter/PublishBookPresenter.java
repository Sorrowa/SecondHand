package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.zzh.View.PublishBookActivity;

public class PublishBookPresenter implements BasePresenter<PublishBookActivity>{


    private PublishBookActivity mview;

    public PublishBookPresenter(PublishBookActivity mview){
        this.bindView(mview);
    }


    @Override
    public void detachView() {
        mview=null;

    }

    @Override
    public void bindView(PublishBookActivity view) {
        mview=view;
    }
}
