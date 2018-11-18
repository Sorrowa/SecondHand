package com.example.zhangzihao.secondhand.syf.model;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zhangzihao.secondhand.Base.URL.USER_LOGIN;

public class LoginModel extends UserModel {
    private static final String TAG = "LoginModel";
    private String email,pwd;

    @Override
    public UserModel params(String... params) {
        email = params[0];
        pwd = params[1];
        return this;
    }

    @Override
    public void execute(UserCallback callback) {
        Log.d(TAG, "execute: "+email+"\t"+pwd);
        requestPostAPI(USER_LOGIN,callback);
    }

    public void requestPostAPI(String url, final UserCallback callback) {
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("email",email)
                .add("pwd",pwd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
                callback.onComplete();
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                //Log.d(TAG, data);
                Message msg = parseLogin(data);
                Log.d(TAG, "parseLogin: "+msg.getCode()+"\t"+msg.getMsg());
                callback.onComplete();
                callback.onSuccess(msg);
            }
        });
    }
}
