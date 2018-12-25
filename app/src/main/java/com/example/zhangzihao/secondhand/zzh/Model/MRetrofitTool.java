package com.example.zhangzihao.secondhand.zzh.Model;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MRetrofitTool {
    private static Retrofit retrofit;

    private static Retrofit imageSource;

    private static Retrofit emailSource;

    private static Retrofit publishSource;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){

            retrofit= new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8081/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    /**
     * 获得上传图片的Instance
     * @param session session
     * @return !
     */
    public static Retrofit getPublishImageInstance(final String session){
        if (null==imageSource){
            /**
             * 用拦截器方式添加header
             */
            OkHttpClient.Builder mokHttpClient=new OkHttpClient().newBuilder();
            mokHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request=chain.request();
                    Request request1=request.newBuilder()
                            .header("cookie",session)
                            .method(request.method(),request.body())
                            .build();

                    return chain.proceed(request1);
                }
            });

            imageSource=new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8081/")
                    .client(mokHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return imageSource;
    }

    public static Retrofit getImageSource(){
        if (imageSource==null){
            imageSource= new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return imageSource;

    }

    /**
     * email获取用户book信息，
     * 使用拦截器机制
     * @return 返回添加了header的Retrofit
     */
    public static Retrofit getEmailSource(final String session){
        if (emailSource==null){

            /**
             * 用拦截器方式添加header
             */
            OkHttpClient.Builder mokHttpClient=new OkHttpClient().newBuilder();
            mokHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request=chain.request();
                    Request request1=request.newBuilder()
                            .header("cookie",session)
                            .method(request.method(),request.body())
                            .build();

                    return chain.proceed(request1);
                }
            });


            emailSource= new Retrofit.Builder()
                    .baseUrl("http://132.232.89.108:8081/")
                    .client(mokHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return emailSource;
    }
}
