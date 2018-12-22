package com.example.zhangzihao.secondhand.zzh.View;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Adapter.MyBookAdapter;
import com.example.zhangzihao.secondhand.zzh.Presenter.MyBookPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class MyBook extends BaseActivity implements BaseView<MyBookPresenter> {

    //presenter对象
    private MyBookPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MyBookAdapter myBookAdapter;
    //记录book信息
    private ArrayList<Book> books = new ArrayList<>();

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
        //设置布局形式：暂时为两列listView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myBookAdapter = new MyBookAdapter(books, this);
        recyclerView.setAdapter(myBookAdapter);
        String session=getCurrentSession();
        if (session==null){
            Toast.makeText(this,"你还没有登陆",Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.startBookInfoGet(getCurrentSession(), getCurrentUser());
    }

    /**
     * 注入布局元素
     */
    private void initeView() {
        toolbar = findViewById(R.id.books_toolbar);
        recyclerView = findViewById(R.id.my_books);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    @Override
    public void bindPresenter(MyBookPresenter myBookPresenter) {
        presenter = myBookPresenter;
    }

    @Override
    public void detachPresenter() {
        presenter = null;
    }


    /**
     * 刷新界面
     */
    public void setBookList(ArrayList<Book> books) {
        //todo:数据注入
        this.books.clear();
        if (null!=books){
            this.books.addAll(books);
        }
        myBookAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
