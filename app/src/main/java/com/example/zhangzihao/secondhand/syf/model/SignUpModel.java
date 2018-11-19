package com.example.zhangzihao.secondhand.syf.model;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zhangzihao.secondhand.Base.URL.USER_SIGN;

public class SignUpModel extends UserModel {
    private static final String TAG = "SignUpModel";
    private User user;

    public SignUpModel(User user){
        this.user = user;
    }

    @Override
    public void execute(UserCallback callback) {
        postUser(USER_SIGN,callback);
    }

    private void postUser(String url, final UserCallback callback){
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)//请求的url
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onComplete();
                callback.onFailure("网络出了点小差！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message msg = parseLogin(data);
                Log.d(TAG, "signUp: "+msg.getCode()+"\t"+msg.getMsg());
                callback.onComplete();
                callback.onSuccess(msg);
            }
        });
    }
}
