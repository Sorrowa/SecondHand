package com.example.zhangzihao.secondhand.zzh.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet.MainGetBookInterface;
import com.example.zhangzihao.secondhand.zzh.JavaBean.ImagePublish;
import com.example.zhangzihao.secondhand.zzh.Presenter.PublishBookPresenter;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PublishModel implements BaseModel<PublishBookPresenter> {


    public final String FILE_ROOT="/Second/";

    private PublishBookPresenter p;
    private Retrofit retrofit;

    public PublishModel(PublishBookPresenter p) {
        bindPresenter(p);
    }

    public void publishBook(String bookName, String bookType
            , String introduction, String email, final Uri uri) {
        retrofit = MRetrofitTool.getEmailSource(p.getSession());
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
            public void onResponse(Call<Message> call
                    , Response<Message> response) {
                Message message = response.body();
                if (message == null) {
                    p.showToast("网络请求失败");
                } else {
                    String bookid=message.getMsg();
                    switch (message.getCode()) {
                        case "302":
                            p.showToast("上传成功");
                            try {
                                publishImage(bookid,uri);
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
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

    /**
     * 上传图片
     * @param bookid 图书编号
     * @param uri 图片uri
     */
    private void publishImage(String bookid, Uri uri) throws URISyntaxException {
        MainGetBookInterface mainGetBookInterface=retrofit.create(MainGetBookInterface
                .class);
        //处理url，
        String imagePath=Tool.doForImageUrl(uri,p.mview);

        Bitmap image= BitmapFactory.decodeFile(imagePath);

        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");

        try {
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(
                    new FileOutputStream(file));
            image.compress(Bitmap.CompressFormat.JPEG, 100
                    , bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        ImagePublish imagePublish=new ImagePublish(bookid
                ,file);

        Gson gson=new Gson();
        String route=gson.toJson(imagePublish);

        RequestBody requestBody=RequestBody.create(okhttp3
                        .MediaType
                        .parse("application/json; charset=utf-8")
                ,route);

        Call<Message> call=mainGetBookInterface.publishImage(requestBody);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
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
