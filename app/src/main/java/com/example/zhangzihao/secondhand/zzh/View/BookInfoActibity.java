package com.example.zhangzihao.secondhand.zzh.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;
import com.example.zhangzihao.secondhand.zzh.Presenter.BookInfoPresenter;

public class BookInfoActibity extends AppCompatActivity implements BaseView<BookInfoPresenter>{


    private int bookId;

    private BookInfoPresenter presenter;

    private ImageView bookImage;

    private TextView name;
    private TextView type;
    private TextView email;
    private TextView introduction;

    private Button agreeButton;
    private Button disagreeButton;

    //显示的书籍
    private Book book;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_actibity);
        bookId= (int) savedInstanceState.get("book");

        presenter=BookInfoPresenter.getInstance();
        presenter.bindView(this);
        //todo:调用presenter，获得book信息

        initeView();

        initeBook();

        addView();
    }

    /**
     * 使用book填充view
     */
    private void addView() {
        name.setText(book.getName());
        type.setText(book.getType());
        email.setText(book.getEmail());
        introduction.setText(book.getIntroduction());
    }

    /**
     * 初始化book
     */
    private void initeBook() {
        presenter.getBook(book);
    }

    /**
     * 初始化view
     */
    private void initeView() {
        name=findViewById(R.id.book_info_name);
        type=findViewById(R.id.book_info_type);
        email=findViewById(R.id.book_info_email);
        introduction=findViewById(R.id.book_info_introduction);

        agreeButton=findViewById(R.id.agree_button);
        disagreeButton=findViewById(R.id.disagree_button);
    }


    @Override
    public void bindPresenter(BookInfoPresenter basePresenter) {}

    @Override
    public void detachPresenter(BookInfoPresenter basePresenter) {}


    @Override
    protected void onDestroy() {
        presenter.detachView(this);
        presenter.detachAll();
        super.onDestroy();
    }
}
