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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zhangzihao.secondhand.JavaBean.Book;
import com.example.zhangzihao.secondhand.MainActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.dyx.adapter.DealRvAdapter;
import com.example.zhangzihao.secondhand.dyx.base.impl.MvpViewImpl;
import com.example.zhangzihao.secondhand.dyx.contract.Contract;
import com.example.zhangzihao.secondhand.dyx.model.DealModel;
import com.example.zhangzihao.secondhand.dyx.presenter.DealEventListener;
import com.example.zhangzihao.secondhand.dyx.presenter.DealPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

public class DealFragment extends MvpViewImpl<DealEventListener> implements Contract.IDealView {
    private RecyclerView mRecyclerView;
    private DealRvAdapter mAdapter;
    private ArrayList<Book> mData = new ArrayList<>();
    private Contract.IDealPresenter mPresenter;
    private String mEmail;
    private SmartRefreshLayout mRefreshLayout;
    private String mSession;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.fragment_deal_toolbar);
        ((MainActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);


        mPresenter = new DealPresenter();
        mPresenter.setView(this);
        mPresenter.setModel(new DealModel(mPresenter));
        mPresenter.attach();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_deal_recyclerView);
        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.fragment_deal_refresh_layout);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (isLogin()) {
                    loadDealBook();
                } else {
                    //设置未
                    iniNoSign();
                }
            }
        });

        mAdapter = new DealRvAdapter(getActivity());
        mAdapter.setOnClickListener(new DealRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, final Book book) {
                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                        .positiveText("确认")
                        .negativeText("取消")
                        .title("提示")
                        .content("是否确认交易?\n买家：" + book.getChanger())
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                switch (which) {
                                    case NEGATIVE:
                                        dialog.dismiss();
                                        Toast.makeText(getActivity(), "取消交易...", Toast.LENGTH_SHORT).show();
                                        break;
                                    case POSITIVE:
                                        getListener().confirmDeal(mSession, book.getBookId());
                                        break;
                                }
                            }
                        })
                        .build();
                dialog.show();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        if (isLogin()) {
            loadDealBook();
        } else {
            iniNoSign();
        }

        return view;
    }

    private void iniNoSign() {
        mAdapter.setEmptyData();
        mRefreshLayout.finishRefresh();
    }

    private void loadDealBook() {
        if (getListener() != null) {
            getListener().loadDealBook(mEmail);
        }
    }


    private boolean isLogin() {
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("user_data",MODE_PRIVATE );
        return (mSession = pref.getString("session", null)) != null && (mEmail = pref.getString("email",null)) != null;
    }

    @Override
    public void initRecyclerView(List<Book> list) {
        mAdapter.setData(list, mEmail);
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        //重新加载一次
        loadDealBook();
    }
}
