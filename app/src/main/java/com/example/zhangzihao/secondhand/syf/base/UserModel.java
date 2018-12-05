package com.example.zhangzihao.secondhand.syf.base;


import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class UserModel<T> {
    protected String[] mParams;
    /**
     *设置数据请求参数
     * @param args 参数数组
     */
    public UserModel params(String... args){
        mParams = args;
        return this;
    }

    public abstract void execute(UserCallback<T> callback);

//    public void requestGetAPI(String url,UserCallback<T> callback){}
//    public void requestPostAPI(String url,UserCallback<T> callback){}

    protected Message parseLogin(String jsonData){
        Gson gson = new Gson();
        Message msg = gson.fromJson(jsonData,Message.class);
        return msg;
    }

    protected User parseUser(String jsonData){
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData,User.class);
        return user;
    }

    public void requestUser(String url,String email,String session,final UserCallback callback){
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Request.Builder reqBuild = new Request.Builder()
                .addHeader("cookie",session);
        HttpUrl.Builder urlBuilder =HttpUrl.parse(url)
                .newBuilder();
        urlBuilder.addQueryParameter("email", email);
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("获取用户信息出错！");
                callback.onComplete();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                User user = parseUser(data);
                callback.onSuccess(user);
                callback.onComplete();
            }
        });
    }
}
