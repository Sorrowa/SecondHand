package com.example.zhangzihao.secondhand.zzh.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MRetrofitTool {
    private static Retrofit retrofit;

    private static Retrofit imageSource;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8081/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getImageSource(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
