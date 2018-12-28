package com.example.zhangzihao.secondhand.dyx.view;

/*
    create by:loser
    date:2018/12/29 3:22
*/

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.JavaBean.MessageBean;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.adapter.MyMessageRvAdapter;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpActivityImpl;
import com.example.zhangzihao.secondhand.dyx.contract.MyMessageContract;
import com.example.zhangzihao.secondhand.dyx.presenter.MyMessageEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class MyMessageView extends MvpActivityImpl<MyMessageEventListener> implements MyMessageContract.IMyMessageView {
    private MyMessageRvAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mEmail;
    private String mSession;



    public MyMessageView(final Activity activity) {
        super(activity);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.activity_my_message_toolbar);
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        mRecyclerView = (RecyclerView) activity.findViewById(R.id.activity_my_message_recyclerView);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        mAdapter = new MyMessageRvAdapter(activity);
        mAdapter.setComment(new ArrayList<MessageBean>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setData(List<MessageBean> books) {
        mAdapter.setComment(books);
    }

    @Override
    public void getMyMessage() {
        if (isLogin()) {
            getListener().getMyMessage(mSession, mEmail);
        } else {
            Toast.makeText(getActivity(), "请先登入...", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("user_data",MODE_PRIVATE );
        return (mSession = pref.getString("session", null)) != null && (mEmail = pref.getString("email",null)) != null;
    }
}
