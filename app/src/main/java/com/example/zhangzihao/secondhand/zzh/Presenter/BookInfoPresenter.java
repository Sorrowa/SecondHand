package com.example.zhangzihao.secondhand.zzh.Presenter;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Model.BookInfoModel;
import com.example.zhangzihao.secondhand.zzh.View.BookInfoActibity;

/**
 * 单例
 */
public class BookInfoPresenter implements BasePresenter<BookInfoActibity> {

    //private BookInfoActibity bookInfoActibity;

    BookInfoActibity v;

    BookInfoModel infoModel;

    private static Boolean OK=false;

    private static BookInfoPresenter p;

    public static BookInfoPresenter getInstance(){
        if (p==null){
            p=new BookInfoPresenter();
            p.infoModel=new BookInfoModel();
            initPresenter();
            OK=Boolean.TRUE;
        }
        return p;
    }

    public static void initPresenter() {
        if (OK==Boolean.TRUE) {
            p.infoModel = new BookInfoModel();
        }
    }

    /**
     * 获取book信息
     * @param bookId book信息保存在这个对象中
     */
    public void getBook(int bookId){
        infoModel.getBook(bookId);
    }

    public void setBookView(Book book){
        v.setBookView(book);
    }


    @Override
    public void detachView() {
        v=null;
    }

    @Override
    public void bindView(BookInfoActibity view) {
        v=view;
    }

    public void detachAll() {
        infoModel.detachPresenter();
    }

    public void downBook(String session, Integer bookId) {
        infoModel.downBook(session, bookId);
    }


    public void showSuccess(String msg) {
        v.show(msg);
    }
}
