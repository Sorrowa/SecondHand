package com.example.zhangzihao.secondhand.syf.model;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.zhangzihao.secondhand.Base.URL.USER_SELECT;

public class InfoModel extends UserModel {
    private static final String TAG = "InfoModel";
    private String email;
    @Override
    public void execute(UserCallback callback) {
        email = mParams[0];
        requestUser(USER_SELECT,callback);
    }

    public void requestUser(String url,final UserCallback callback){
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder =HttpUrl.parse(url)
                .newBuilder();
        urlBuilder.addQueryParameter("email", email);
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
                callback.onFailure("获取用户信息出错！");
                callback.onComplete();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                User user = parseUser(data);
                Log.d(TAG, "onResponse: "+user.getName()+"\t"+user.getHeadPath());
                callback.onSuccess(user);
                callback.onComplete();
            }
        });
    }
}
