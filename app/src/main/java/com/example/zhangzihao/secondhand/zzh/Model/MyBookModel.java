package com.example.zhangzihao.secondhand.zzh.Model;


import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet.MainGetBookInterface;
import com.example.zhangzihao.secondhand.zzh.Presenter.MyBookPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyBookModel implements BaseModel<MyBookPresenter> {

    private MyBookPresenter presenter;

    public MyBookModel(MyBookPresenter presenter){
        bindPresenter(presenter);
    }


    @Override
    public void bindPresenter(MyBookPresenter p) {
        this.presenter=p;
    }

    @Override
    public void detachPresenter() {
        this.presenter=null;
    }

    /**
     * 设置图书详细信息
     */
    public void getBookInfo(String session,String email){
        Retrofit retrofit=MRetrofitTool.getEmailSource(session);
        MainGetBookInterface mainGetBookInterface=retrofit.create(MainGetBookInterface
                .class);
        Call<ArrayList<Book>> call=mainGetBookInterface.getBookByEmail(email);
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call
                    , Response<ArrayList<Book>> response) {
                presenter.setBooks(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {}
        });

    }
}
