package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.zzh.View.MyBook;

public class MyBookPresenter implements BasePresenter<MyBook> {


    //view层对象
    private MyBook mybook;


    public MyBookPresenter(MyBook myBook){
        bindView(myBook);
    }


    @Override
    public void detachView(MyBook view) {
        mybook=null;
    }

    @Override
    public void bindView(MyBook view) {
        mybook=view;
    }

    /**
     * 获取book信息
     */
    public void startBookInfoGet() {
    }
}
