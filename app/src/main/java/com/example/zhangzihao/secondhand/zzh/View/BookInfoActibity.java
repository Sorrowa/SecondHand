package com.example.zhangzihao.secondhand.zzh.View;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.zzh.Presenter.BasePresenter;
import com.example.zhangzihao.secondhand.zzh.Presenter.BookInfoPresenter;

import java.util.Objects;

import static com.example.zhangzihao.secondhand.Base.URL.IMGS;

public class BookInfoActibity extends AppCompatActivity implements BaseView<BookInfoPresenter>{


    private int bookId;

    private BookInfoPresenter presenter;

    private ImageView bookImage;

    private TextView name;
    private TextView type;
    private TextView email;
    private TextView introduction;


    private Toolbar toolbar;
    //打分控件
    private RatingBar ratingBar;


    Handler handler=new Handler();

    private Book mBook;
    private TextView mDown;
    private String mSession;
    private String mUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_two_version);
        bookId= getIntent().getIntExtra("book",-1);

        presenter=BookInfoPresenter.getInstance();
        presenter.bindView(this);
        //todo:调用presenter，获得book信息

        initeView();

        initeActionBar();

        initeBook();

        mDown = (TextView) findViewById(R.id.book_info_down);

        mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin() && mBook != null) {
                    MaterialDialog dialog = new MaterialDialog.Builder(BookInfoActibity.this)
                            .positiveText("确定")
                            .negativeText("取消")
                            .content("是否下架《" + mBook.getName() +"》")
                            .title("请确认!")
                            .onAny(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    switch (which) {
                                        case POSITIVE:
                                            presenter.downBook(mSession, mBook.getBookId());
                                            break;
                                        case NEGATIVE:
                                            Toast.makeText(BookInfoActibity.this, "取消下架", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            })
                            .build();
                    dialog.show();
                } else {
                    Toast.makeText(BookInfoActibity.this, "出错了...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    /**
     * 初始化标题栏
     */
    private void initeActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setTitle(null);

        //显示返回键
    }

    /**
     * 使用book填充view
     */
    private void addView(Book book) {
        mBook = book;
        name.setText("图书名称: "+book.getName());
        type.setText("图书类型: "+book.getType());
        email.setText("联系人邮箱: "+book.getEmail());
        introduction.setText("图书基本介绍: "+book.getIntroduction());
        if (book.getImgPath()!=null){
            Glide.with(this)
                    .load(IMGS+book.getImgPath())
                    .into(bookImage);
        }
    }

    /**
     * 初始化book
     */
    private void initeBook() {
        presenter.getBook(bookId);
    }

    /**
     * 初始化view
     */
    private void initeView() {
        name=findViewById(R.id.book_info_name);
        type=findViewById(R.id.book_info_type);
        email=findViewById(R.id.book_info_email);
        introduction=findViewById(R.id.book_info_introduction);
        toolbar=findViewById(R.id.info_toolbar);
        bookImage=findViewById(R.id.image_book);
    }


    @Override
    public void bindPresenter(BookInfoPresenter basePresenter) {}

    @Override
    public void detachPresenter() {}

    /**
     * 异步填充view
     * @param book
     */
    public void setBookView(final Book book){
        Log.d("zzh","bookId is "+book);
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Log.d("zzh","book is "+book);
                book.setBookId(bookId);
                addView(book);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        onDestroy();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        presenter.detachAll();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("zzh","点击成功了吗");
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(this.getSharedPreferences("user_data",MODE_PRIVATE ));
        return (mSession = pref.getString("session", null)) != null && (mUserEmail = pref.getString("email",null)) != null;
    }

    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }
}
