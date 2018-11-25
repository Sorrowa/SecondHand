package com.example.zhangzihao.secondhand.zzh.Model;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Presenter.BookInfoPresenter;

import retrofit2.Retrofit;

public class BookInfoModel implements BaseModel <BookInfoPresenter>{

    private BookInfoPresenter presenter;


    public Book getBook(Book book){
        //todo:根据bookid获取book信息
        Retrofit retrofit=MRetrofitTool.getRetrofitInstance();
        return null;
    }



    @Override
    public void bindPresenter(BookInfoPresenter p) {
        presenter=p;
    }

    @Override
    public void detachPresenter() {
        presenter=null;
    }
}
