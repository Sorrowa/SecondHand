package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.zzh.View.BookInfoActibity;

/**
 * 单例
 */
public class BookInfoPresenter implements BasePresenter<BookInfoActibity> {

    private BookInfoActibity bookInfoActibity;

    private static BookInfoPresenter p;

    public static BookInfoPresenter getInstance(){
        if (p==null){
            p=new BookInfoPresenter();
        }
        return p;
    }



    @Override
    public void detachView(BookInfoActibity view) {

    }

    @Override
    public void bindView(BookInfoActibity view) {

    }
}
