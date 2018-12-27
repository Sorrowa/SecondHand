package com.example.zhangzihao.secondhand.dyx.model.service;

/*
    create by:loser
    date:2018/12/26 1:45
*/

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.JavaBean.Response;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @GET("change")
    Observable<Response> dealBook(@Header("cookie") String session,
                              @Query("changer") String email,
                                 @Query("bookId") Integer id);

    @GET("confirm")
    Observable<Response> getConfirmBook(@Header("cookie") String session,
                                          @Query("bookId") Integer id);

    @GET("selectChanges")
    Observable<List<Book>> getDealBook(@Query("email") String email);

    @GET("selectWithComments")
    Observable<Data> getBook(@Query("bookId") Integer bookId);


    @POST("insert")
    Observable<Integer> insertComment(@Header("cookie") String session,
                                      @Body RequestBody body);
}
