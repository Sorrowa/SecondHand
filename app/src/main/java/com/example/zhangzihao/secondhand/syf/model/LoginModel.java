package com.example.zhangzihao.secondhand.syf.model;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserModel;
import com.example.zhangzihao.secondhand.syf.fragment.LoginCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.zhangzihao.secondhand.Base.URL.USER_LOGIN;
import static com.example.zhangzihao.secondhand.Base.URL.USER_RESET;

public class LoginModel extends UserModel {
    private static final String TAG = "LoginModel";
    private String email,pwd;
    private OkHttpClient okHttpClient  = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    public LoginModel(){}
    public LoginModel(String email,String pwd){
        this.email = email;
        this.pwd = pwd;
    }

//    @Override
//    public UserModel params(String... params) {
//        email = params[0];
//        return this;
//    }

    @Override
    public void execute(UserCallback callback) {
        email = mParams[0];
        Log.d(TAG, "execute: "+email);
        resetPwd(USER_RESET,callback);
    }

    public void execute(LoginCallback callback){
        Log.d(TAG, "execute: "+email+"\t"+pwd);
        requestPostAPI(USER_LOGIN,callback);
    }

    private void resetPwd(String url, final UserCallback callback){
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
                Log.d(TAG, "onFailure: ");
                callback.onComplete();
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Message msg = parseLogin(data);
                callback.onComplete();
                callback.onSuccess(msg);
            }
        });
    }

    private void requestPostAPI(String url, final LoginCallback callback) {
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
                Message msg = parseLogin(data);
                String sessionid;
                if (msg.getCode().equals("103")) {
                    Log.d(TAG, "parseLogin: " + msg.getCode() + "\t" + msg.getMsg());
                    Headers headers = response.headers();
                    Log.d("info_headers", "header " + headers);
                    List<String> cookies = headers.values("Set-Cookie");
                    String session = cookies.get(0);
                    Log.d("info_cookies", "onResponse-size: " + cookies);
                    sessionid = session.substring(0, session.indexOf(";"));
                    Log.i("info_s", "session is  :" + sessionid);

                }else{
                    sessionid = null;
                }
                callback.onComplete();
                callback.loginSuccess(msg,sessionid);
            }
        });
    }
}
