package com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet;

import com.example.zhangzihao.secondhand.JavaBean.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface MainGetBookInterface {

    @POST("book/publish")
    Call<ArrayList<Book>> getBookInfo();
}
