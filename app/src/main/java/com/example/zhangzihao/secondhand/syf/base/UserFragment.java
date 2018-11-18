package com.example.zhangzihao.secondhand.syf.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class UserFragment extends Fragment implements UserView {

    private ProgressDialog mProgressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("正在加载用户信息！");
    }

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showErr() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
