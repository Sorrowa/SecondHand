package com.example.zhangzihao.secondhand.dyx.activity;

/*
    create by:loser
    date:2018/12/29 2:05
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.contract.MyBookContract;
import com.example.zhangzihao.secondhand.dyx.model.MyBookModel;
import com.example.zhangzihao.secondhand.dyx.presenter.MyBookPresenter;
import com.example.zhangzihao.secondhand.dyx.view.MyBookView;

public class MyBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_layout);

        MyBookPresenter presenter = new MyBookPresenter();
        presenter.setView(new MyBookView(this));
        presenter.setModel(new MyBookModel(presenter));
        presenter.attach();
    }
}
