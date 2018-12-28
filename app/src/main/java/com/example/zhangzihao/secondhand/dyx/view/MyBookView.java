package com.example.zhangzihao.secondhand.dyx.view;

/*
    create by:loser
    date:2018/12/29 0:39
*/

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.adapter.MyBookRvAdapter;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpActivityImpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyBookContract;
import com.example.zhangzihao.secondhand.dyx.presenter.MyBookLEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class MyBookView extends MvpActivityImpl<MyBookLEventListener> implements MyBookContract.IMyBookView {
    private RecyclerView mRecyclerView;
    private MyBookRvAdapter mAdapter;
    private String mName;
    private String mEmail;
    private String mSession;
    private int mId;

    public MyBookView(final Activity activity) {
        super(activity);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.activity_my_book_toolbar);
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "取消交换...", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        });

        Intent intent = activity.getIntent();
        mName = intent.getStringExtra("book_name");
        mId = intent.getIntExtra("book_id", -1);


        mRecyclerView = (RecyclerView) activity.findViewById(R.id.activity_my_book_recyclerView);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        mAdapter = new MyBookRvAdapter(activity);
        mAdapter.setListener(new MyBookRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, final Book book) {
                MaterialDialog dialog = new MaterialDialog.Builder(activity)
                        .title("请确认")
                        //需要添加书名
                        .content("是否使用《" + book.getName() + "》交换《" + mName + "》？")
                        .positiveText("确认")
                        .negativeText("取消")
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                switch (which) {
                                    case NEGATIVE:
                                        Toast.makeText(getActivity(), "取消交换...", Toast.LENGTH_SHORT).show();
                                        break;
                                    case POSITIVE:
                                        if (mId == -1) {
                                            Toast.makeText(getActivity(), "出错了...", Toast.LENGTH_SHORT).show();
                                        } else {
                                            getListener().changeBook(mSession, mEmail, mId, book.getBookId());
                                        }
                                        break;
                                }
                            }
                        })
                        .build();
                dialog.show();
            }
        });
        mAdapter.setList(new ArrayList<Book>());
        mRecyclerView.setAdapter(mAdapter);
    }




    private boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("user_data",MODE_PRIVATE );
        return (mSession = pref.getString("session", null)) != null && (mEmail = pref.getString("email",null)) != null;
    }

    @Override
    public void setData(List<Book> books) {
        mAdapter.setList(books);
    }

    @Override
    public void getMyBook() {
        if (isLogin()) {
            getListener().getMyBook(mSession, mEmail);
        } else {
            Toast.makeText(getActivity(), "请先登入...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
