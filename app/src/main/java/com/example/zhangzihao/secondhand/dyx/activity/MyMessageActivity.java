package com.example.zhangzihao.secondhand.dyx.activity;

/*
    create by:loser
    date:2018/12/29 3:24
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.model.MyMessageModel;
import com.example.zhangzihao.secondhand.dyx.presenter.MyMessagePresenter;
import com.example.zhangzihao.secondhand.dyx.view.MyMessageView;

public class MyMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        MyMessagePresenter presenter = new MyMessagePresenter();
        presenter.setView(new MyMessageView(this));
        presenter.setModel(new MyMessageModel(presenter));
        presenter.attach();
    }
}
