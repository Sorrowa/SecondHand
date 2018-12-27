package com.example.zhangzihao.secondhand.dyx.model.service;

/*
    create by:loser
    date:2018/12/26 22:50
*/


import com.example.zhangzihao.secondhand.Base.URL;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.JavaBean.Response;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private Retrofit mRetrofit;

    public ApiService() {
        Client client = Client.getInstance();
        mRetrofit = client.getClient();
    }

    public Observable<List<Book>> getDealBook(String email) {
        return mRetrofit.create(API.class).getDealBook(email);
    }

    public Observable<Response> getConfirmBook(String session, Integer id) {
        return mRetrofit.create(API.class).getConfirmBook(session, id);
    }

    public Observable<Response> buyBook(String session, String email, Integer bookId) {
        return mRetrofit.create(API.class).dealBook(session, email, bookId);
    }


    public Observable<Data> getBook(Integer bookId) {
        return mRetrofit.create(API.class).getBook(bookId);
    }

    public Observable<Integer> insertComment(String session, String comment) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.COMMENT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), comment);
        return retrofit.create(API.class).insertComment(session, body);
    }
}
