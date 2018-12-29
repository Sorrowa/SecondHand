package com.example.zhangzihao.secondhand.dyx.view;

/*
    create by:loser
    date:2018/12/27 1:27
*/

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.zhangzihao.secondhand.Base.URL;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.JavaBean.Comment;
import com.example.zhangzihao.secondhand.JavaBean.Data;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.activity.MyBookActivity;
import com.example.zhangzihao.secondhand.dyx.adapter.CommentRvAdapter;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpActivityImpl;
import com.example.zhangzihao.secondhand.dyx.contract.BookContract;
import com.example.zhangzihao.secondhand.dyx.presenter.BookEventListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

public class BookView extends MvpActivityImpl<BookEventListener> implements BookContract.IBookView {
    private TextView mName;
    private TextView mType;
    private TextView mIntroduction;
    private TextView mEmail;
    private ImageView mImage;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private String mUserEmail;
    private String mSession;
    private CommentRvAdapter mAdapter;
    private Book mBook;
    private RelativeLayout mLayout;
    private TextView mBuy;


    public BookView(final Activity activity) {
        super(activity);

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.activity_book_toolbar);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        mLayout = (RelativeLayout) activity.findViewById(R.id.activity_book_comment_container);

        mBuy = (TextView) activity.findViewById(R.id.activity_book_buy);
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MyBookActivity.class);
                intent.putExtra("book_name", mBook.getName());
                intent.putExtra("book_id", mBook.getBookId());
                getActivity().startActivity(intent);
            }
        });

        mName = (TextView) activity.findViewById(R.id.activity_book_book_name);
        mType = (TextView) activity.findViewById(R.id.activity_book_book_type);
        mIntroduction = (TextView) activity.findViewById(R.id.activity_book_book_introduction);
        mEmail = (TextView) activity.findViewById(R.id.activity_book_email);
        mImage = (ImageView) activity.findViewById(R.id.activity_book_book_image);

        //set book
        Bundle bundle = activity.getIntent().getBundleExtra("book_bundle");
        mBook = bundle.getParcelable("book");
        if (mBook != null) {
            initBook(mBook);
        }

        final EditText edit = (EditText) activity.findViewById(R.id.activity_book_comment_edit);
        final Button submit = (Button) activity.findViewById(R.id.activity_book_comment_submit);


        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    submit.setEnabled(false);
                } else {
                    submit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin()) {
                    String comment = edit.getText().toString();
                    getListener().submitComment(mSession, mUserEmail, comment, mBook.getBookId());
                    edit.setText("");
                } else {
                    Toast.makeText(activity, "请先登入...", Toast.LENGTH_SHORT).show();
                }
                edit.clearFocus();
                mLayout.setFocusable(true);
                //关闭输入法
                InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken()
                            ,InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        mRefreshLayout = (SmartRefreshLayout) activity.findViewById(R.id.activity_book_refresh_layout);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mBook != null) {
                    getListener().getComment(mBook.getBookId());
                } else {
                    mRefreshLayout.finishRefresh();
                }
            }
        });


        mRecyclerView = (RecyclerView) activity.findViewById(R.id.activity_book_comment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new CommentRvAdapter(activity);
        mAdapter.setComment(new ArrayList<Data.CommentsBean>());
        mRecyclerView.setAdapter(mAdapter);
    }


    @SuppressLint("SetTextI18n")
    private void initBook(Book book) {
        mName.setText("图书名称：" + book.getName());
        mType.setText("图书类型：" + book.getType());
        mIntroduction.setText("简介：" + book.getIntroduction());
        mEmail.setText("发布者邮箱：" + book.getEmail());
        Glide.with(getActivity()).load(URL.IMGS + book.getImgPath()).into(mImage);
    }

    private boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("user_data",MODE_PRIVATE );
        return (mSession = pref.getString("session", null)) != null && (mUserEmail = pref.getString("email",null)) != null;
    }


    @Override
    public void initComment(List<Data.CommentsBean> comments) {
        if (comments == null) {
            mAdapter.setComment(new ArrayList<Data.CommentsBean>());
        } else {
            mAdapter.setComment(comments);
        }
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void refreshComment() {
        if (mBook != null) {
            getListener().getComment(mBook.getBookId());
        }
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
