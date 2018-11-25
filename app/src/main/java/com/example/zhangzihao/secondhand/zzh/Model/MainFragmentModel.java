package com.example.zhangzihao.secondhand.zzh.Model;

import android.util.Log;

import com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet.MainGetBookInterface;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragmentModel implements BaseModel {

    public MainFragmentModel(){

    }

    /**
     * 获取书籍列表,不设置全局books变量，防止内存泄漏
     * @return books
     */
    public ArrayList<Book> getBookInfo(){

        final ArrayList<Book> books=new ArrayList<>();



        Retrofit retrofit=MRetrofitTool.getRetrofitInstance();

        MainGetBookInterface mainGetBookInterface=retrofit.create(MainGetBookInterface
                .class);

        Call<ArrayList<Book>> call=mainGetBookInterface.getBookInfo();

        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                books.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Log.e("zzh","something wrong in Internet "+t);

            }
        });

        return books;
    }

    public ArrayList<Book> seekForBookInfo() {
        return null;
    }

    @Override
    public void bindPresenter(BasePresenter p) {

    }

    @Override
    public void detachPresenter() {

    }
}
