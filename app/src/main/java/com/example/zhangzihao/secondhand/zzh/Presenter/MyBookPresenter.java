package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.Base.BaseActivity;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Model.MyBookModel;
import com.example.zhangzihao.secondhand.zzh.View.MyBook;

import java.util.ArrayList;

public class MyBookPresenter implements BasePresenter<MyBook> {


    //view层对象
    private MyBook mybook;
    //Model层对象
    private MyBookModel myBookModel;


    public MyBookPresenter(MyBook myBook){
        bindView(myBook);
        myBookModel=new MyBookModel(this);
    }


    @Override
    public void detachView() {
        mybook=null;
    }

    @Override
    public void bindView(MyBook view) {
        mybook=view;
    }

    /**
     * 获取book信息
     */
    public void startBookInfoGet(String session,String email) {
        myBookModel.getBookInfo(session,email);
    }

    /**
     * 设置图书信息
     */
    public void setBooks(ArrayList<Book> books){
        mybook.setBookList(books);
    }
}
