package com.example.zhangzihao.secondhand.dyx.activity;

/*
    create by:loser
    date:2018/12/27 1:28
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.contract.BookContract;
import com.example.zhangzihao.secondhand.dyx.model.BookModel;
import com.example.zhangzihao.secondhand.dyx.presenter.BookPresenter;
import com.example.zhangzihao.secondhand.dyx.view.BookView;

public class BookActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        BookContract.IBookPresenter presenter = new BookPresenter();
        presenter.setView(new BookView(this));
        presenter.setModel(new BookModel(presenter));
        presenter.attach();
    }
}
