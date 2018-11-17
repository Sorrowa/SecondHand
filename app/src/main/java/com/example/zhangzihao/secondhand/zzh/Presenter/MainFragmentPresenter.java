package com.example.zhangzihao.secondhand.zzh.Presenter;


import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Model.MainFragmentModel;

import java.util.ArrayList;

/**
 * 给主碎片进行数据传输的Presenter
 */
public class MainFragmentPresenter implements BasePresenter {

    private MainFragmentModel mainFragmentModel;

    public MainFragmentPresenter(){
        mainFragmentModel=new MainFragmentModel();
    }

    public ArrayList<Book> getBookInfo(){

        return mainFragmentModel.getBookInfo();
    }

    public ArrayList<Book> seekForBookInfo() {
        return mainFragmentModel.seekForBookInfo();
    }

}
