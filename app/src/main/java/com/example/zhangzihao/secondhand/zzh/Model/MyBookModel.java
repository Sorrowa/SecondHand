package com.example.zhangzihao.secondhand.zzh.Model;


import com.example.zhangzihao.secondhand.zzh.Presenter.MyBookPresenter;

public class MyBookModel implements BaseModel<MyBookPresenter> {

    private MyBookPresenter presenter;

    public MyBookModel(MyBookPresenter presenter){
        bindPresenter(presenter);
    }


    @Override
    public void bindPresenter(MyBookPresenter p) {
        this.presenter=p;
    }

    @Override
    public void detachPresenter() {

    }
}
