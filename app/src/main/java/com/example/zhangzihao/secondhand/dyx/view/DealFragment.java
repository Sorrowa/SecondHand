package com.example.zhangzihao.secondhand.dyx.view;

/*
    create by:loser
    date:2018/12/26 0:25
*/

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.adapter.DealRvAdapter;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpViewImpl;
import com.example.zhangzihao.secondhand.dyx.contract.Contract;
import com.example.zhangzihao.secondhand.dyx.model.DealModel;
import com.example.zhangzihao.secondhand.dyx.presenter.DealEventListener;
import com.example.zhangzihao.secondhand.dyx.presenter.DealPresenter;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class DealFragment extends MvpViewImpl<DealEventListener> implements Contract.IDealView {
    private TextView mText;
    private RecyclerView mRecyclerView;
    private DealRvAdapter mAdapter;
    private ArrayList<Book> mData = new ArrayList<>();
    private Contract.IDealPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, container, false);

        mPresenter = new DealPresenter();
        mPresenter.setView(this);
        mPresenter.setModel(new DealModel());
        mPresenter.onAttach();

        mText = (TextView) view.findViewById(R.id.fragment_deal_text);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_deal_recyclerView);

        if (isLogin()) {
            loadDealBook();
        } else {
            initText();
        }

        return view;
    }

    private void initText() {
        mText.setText("没有登入...");
        mText.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void loadDealBook() {

    }


    boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("user_data",MODE_PRIVATE );
        return pref.getString("session", null) != null;
    }

    @Override
    public void initRecyclerView(ArrayList<Book> list) {
        mAdapter = new DealRvAdapter(getActivity());
        mAdapter.setOnClickListener(new DealRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {

            }
        });
        mAdapter.setData(list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mText.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
