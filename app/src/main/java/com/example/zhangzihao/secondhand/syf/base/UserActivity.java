package com.example.zhangzihao.secondhand.syf.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.Base.BaseActivity;

public abstract class UserActivity extends BaseActivity implements UserView {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("稍等一下！");
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
        Looper.prepare();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void showErr() {
        showToast("网络出现错误！");
    }

    @Override
    public Context getContext() {
        return UserActivity.this;
    }
}
