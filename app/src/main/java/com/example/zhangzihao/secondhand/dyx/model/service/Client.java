package com.example.zhangzihao.secondhand.dyx.model.service;

/*
    create by:loser
    date:2018/12/26 22:27
*/

import com.example.zhangzihao.secondhand.Base.URL;
import com.example.zhangzihao.secondhand.JavaBean.Book;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Client sInstance;
    private Retrofit mRetrofit;

    private Client() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL.BOOK)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static Client getInstance() {
        if (sInstance == null) {
            synchronized (Client.class) {
                if (sInstance == null) {
                    sInstance = new Client();
                }
            }
        }
        return sInstance;
    }

    public Retrofit getClient() {
        return mRetrofit;
    }
}
