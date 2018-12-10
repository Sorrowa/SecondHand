package com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet;

import com.example.zhangzihao.secondhand.JavaBean.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainGetBookInterface {

    @GET("/book/selectAll")
    Call<ArrayList<Book>> getBookInfo();

    @GET("/book/selectWithComments")
    Call<Book> getBook(@Query("bookId") int bookId);

    @GET("/book/selectByName")
    Call<ArrayList<Book>> getBookByName(@Query("name")String name);

    @GET("/book/selectByType")
    Call<ArrayList<Book>> getBookByType(@Query("type")String type);

    @GET("/book/selectByEmail")
    Call<ArrayList<Book>> getBookByEmail(@Query("email")String email);
}
