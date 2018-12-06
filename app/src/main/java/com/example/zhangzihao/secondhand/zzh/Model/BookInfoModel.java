package com.example.zhangzihao.secondhand.zzh.Model;

import android.util.Log;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.zzh.InternetInterfaceSet.MainGetBookInterface;
import com.example.zhangzihao.secondhand.zzh.Presenter.BookInfoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookInfoModel implements BaseModel <BookInfoPresenter>{

    private BookInfoPresenter presenter;

    public static Book book=new Book();


    public void getBook(final int bookId){
        //todo:根据bookid获取book信息
        //BookInfoPresenter.setPresenter(this);
        presenter=BookInfoPresenter.getInstance();
        Retrofit retrofit=MRetrofitTool.getRetrofitInstance();
        MainGetBookInterface mainGetBookInterface=retrofit.create(MainGetBookInterface
                .class);
        Call<Book> call=mainGetBookInterface.getBook(bookId);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                //用完之后response就被释放了
                //所以不能直接让他们相等
                Book book1=response.body();
                //Log.d("zzh",""+book1.getEmail());
                //Log.d("zzh",""+book1.getName());
                book.setBookId(book1.getBookId());
                book.setBad(book1.getBad());
                book.setDate(book1.getDate());
                book.setEmail(book1.getEmail());
                book.setGood(book1.getGood());
                book.setImgPath(book1.getImgPath());
                book.setImgPath2(book1.getImgPath2());
                book.setImgPath3(book1.getImgPath3());
                book.setIntroduction(book1.getIntroduction());
                book.setName(book1.getName());
                book.setState(book1.getState());
                book.setType(book1.getType());
//                book.setUdDate(book1.getUdDate());
//                Log.d("zzh","presenter ="+presenter.)
                presenter.setBookView(book);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }



    @Override
    public void bindPresenter(BookInfoPresenter p) {
        presenter=p;
    }

    @Override
    public void detachPresenter() {
        presenter=null;
    }
}
