package com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Message;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/book/publish")
    Call<Message> publishBook(@Body RequestBody body);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/upload/books")
    Call<Message> publishImage(@Body RequestBody body);
}
