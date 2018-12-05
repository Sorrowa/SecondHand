package com.example.zhangzihao.secondhand.zzh.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.MyBookPresenter;

public class MyBook extends AppCompatActivity implements BaseView<MyBookPresenter>{

    //presenter对象
    private MyBookPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);

        bindPresenter(new MyBookPresenter(this));

        initeView();

        initeRecycleView();

    }

    /**
     * 初始化recycleView
     */
    private void initeRecycleView() {
    }

    /**
     * 注入布局元素
     */
    private void initeView() {
        toolbar=findViewById(R.id.books_toolbar);
        recyclerView=findViewById(R.id.my_books);
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
