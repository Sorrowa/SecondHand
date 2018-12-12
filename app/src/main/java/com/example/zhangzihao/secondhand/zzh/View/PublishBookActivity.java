package com.example.zhangzihao.secondhand.zzh.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;

public class PublishBookActivity extends AppCompatActivity
        implements BaseView<PublishBookPresenter>{

    private PublishBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_book);
    }

    @Override
    public void bindPresenter(PublishBookPresenter publishBookPresenter) {
        presenter=publishBookPresenter;
    }

    @Override
    public void detachPresenter() {

    }
}
