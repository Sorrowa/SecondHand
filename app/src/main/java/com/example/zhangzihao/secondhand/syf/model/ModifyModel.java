package com.example.zhangzihao.secondhand.syf.model;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.syf.base.UserCallback;
import com.example.zhangzihao.secondhand.syf.base.UserModel;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.zhangzihao.secondhand.Base.URL.UPLOAD_HEADS;
import static com.example.zhangzihao.secondhand.Base.URL.USER_MODIFY;
import static com.example.zhangzihao.secondhand.Base.URL.USER_SELECT_BY_EMAIL;

public class ModifyModel extends UserModel {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private User user;
    private String email;
    private File file;
    public ModifyModel(String email){
        this.email = email;
    }
    public ModifyModel(User user){
        this.user = user;
    }
    public ModifyModel(File file,String email){
        this.file = file;
        this.email = email;
    }

    @Override
    public void execute(UserCallback callback) {
        if (mParams[0].equals("postUser"))
            postUser(USER_MODIFY,callback);
        else if (mParams[0].equals("requestUser")) {
            requestUser(USER_SELECT_BY_EMAIL, email, callback);
        }else
            changeHead(UPLOAD_HEADS,callback);

    }

    private void changeHead(String url,final UserCallback callback){
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "head_image", fileBody)
                .addFormDataPart("email", email)
                .build();
        Request request = new Request.Builder()
                .url(url)
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
                Message message = parseLogin(data);
                callback.onComplete();
                callback.onSuccess(message);
            }
        });
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
                Message message = parseLogin(data);
                callback.onComplete();
                callback.onSuccess(message);
            }
        });
    }
}
