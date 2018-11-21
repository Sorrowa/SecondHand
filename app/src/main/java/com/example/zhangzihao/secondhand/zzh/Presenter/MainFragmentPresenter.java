package com.example.zhangzihao.secondhand.zzh.Presenter;


import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.MainActivity;
import com.example.zhangzihao.secondhand.zzh.Model.MainFragmentModel;
import com.example.zhangzihao.secondhand.zzh.View.BaseView;

import java.util.ArrayList;

/**
 * 给主碎片进行数据传输的Presenter
 */
public class MainFragmentPresenter implements BasePresenter {

    private MainFragmentModel mainFragmentModel;
    private static MainActivity mainActivity;

    public MainFragmentPresenter(MainActivity mainActivity){
        mainFragmentModel=new MainFragmentModel();
        mainFragmentModel.bindPresenter(this);
        MainFragmentPresenter.mainActivity=mainActivity;
    }

    public ArrayList<Book> getBookInfo(){
        return mainFragmentModel.getBookInfo();
    }

    public ArrayList<Book> seekForBookInfo(String content) {
        return mainFragmentModel.seekForBookInfo(content);
    }

    public void setBookList(ArrayList<Book> books){
        //todo:添加book内容
        //Log.d("zzh","books="+books);
        mainActivity.setBookList(books);
    }




    @Override
    public void detachView(BaseView view) { }

    @Override
    public void bindView(BaseView view) { }
}
