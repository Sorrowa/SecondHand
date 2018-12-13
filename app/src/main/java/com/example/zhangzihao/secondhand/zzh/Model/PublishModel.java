package com.example.zhangzihao.secondhand.zzh.Model;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet.MainGetBookInterface;
import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PublishModel implements BaseModel<PublishBookPresenter> {


    private PublishBookPresenter p;

    public PublishModel(PublishBookPresenter p) {
        bindPresenter(p);
    }

    public void publishBook(String bookName, String bookType
            , String introduction, String email) {
        Retrofit retrofit = MRetrofitTool.getEmailSource(p.getSession());
        MainGetBookInterface mainGetBookInterface = retrofit.create(MainGetBookInterface
                .class);

        //todo:转换数据格式
        Book book=new Book();
        book.setName(bookName);
        book.setType(bookType);
        book.setIntroduction(introduction);
        book.setEmail(email);

        Gson gson=new Gson();
        String route=gson.toJson(book);

        RequestBody requestBody=RequestBody.create(okhttp3
                        .MediaType
                        .parse("application/json; charset=utf-8")
                ,route);


        Call<Message> call = mainGetBookInterface.publishBook(requestBody);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message message = response.body();
                if (message == null) {
                    p.showToast("网络请求失败");
                } else {
                    switch (message.getCode()) {
                        case "302":
                            p.showToast("上传成功");
                            break;
                        case "301":
                            p.showToast("上传失败");
                            break;
                        default:
                            p.showToast("发生未知错误");
                            break;
                    }
                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
            }
        });
    }

    @Override
    public void bindPresenter(PublishBookPresenter p) {
        this.p = p;
    }

    @Override
    public void detachPresenter() {
        p = null;
    }
}
