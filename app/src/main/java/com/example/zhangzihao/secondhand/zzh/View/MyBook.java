package com.example.zhangzihao.secondhand.zzh.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.MyBookPresenter;

public class MyBook extends AppCompatActivity implements BaseView<MyBookPresenter>{

    //presenterdu对象
    private MyBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    @Override
    public void bindPresenter(MyBookPresenter myBookPresenter) {
        presenter=myBookPresenter;
    }

    @Override
    public void detachPresenter() {
        presenter=null;
    }
}
