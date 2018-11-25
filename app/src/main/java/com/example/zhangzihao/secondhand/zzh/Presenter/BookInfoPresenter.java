package com.example.zhangzihao.secondhand.zzh.Presenter;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Model.BaseModel;
import com.example.zhangzihao.secondhand.zzh.Model.BookInfoModel;
import com.example.zhangzihao.secondhand.zzh.View.BookInfoActibity;

/**
 * 单例
 */
public class BookInfoPresenter implements BasePresenter<BookInfoActibity> {

    private BookInfoActibity bookInfoActibity;

    BookInfoActibity v;

    BookInfoModel infoModel;

    private static BookInfoPresenter p;

    public static BookInfoPresenter getInstance(){
        if (p==null){
            p=new BookInfoPresenter();
            initPresenter();
        }
        return p;
    }

    private static void initPresenter() {
        p.infoModel=new BookInfoModel();
    }

    /**
     * 获取book信息
     * @param book book信息保存在这个对象中
     */
    public void getBook(Book book){
        infoModel.getBook(book);
    }



    @Override
    public void detachView(BookInfoActibity view) {
        v=null;
    }

    @Override
    public void bindView(BookInfoActibity view) {
        v=view;
    }

    public void detachAll() {
        infoModel.detachPresenter();
    }
}
