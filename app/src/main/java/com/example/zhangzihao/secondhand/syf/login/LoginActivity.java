package com.example.zhangzihao.secondhand.syf.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.MainActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.syf.base.UserActivity;
import com.example.zhangzihao.secondhand.syf.modify.ModifyInfoActivity;
import com.example.zhangzihao.secondhand.syf.presenter.LoginPresenter;
import com.example.zhangzihao.secondhand.syf.signUp.SignUpActivity;
import com.example.zhangzihao.secondhand.syf.view.LoginView;

public class LoginActivity extends UserActivity implements LoginView {


    private static final String TAG = "LoginActivity";
    private EditText ev_email,ev_passwd;
    private Button login_btn;
    private TextView sign_up,find_pwd;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter();
        presenter.attachView(this);

        initView();
    }

    private void initView() {
        ev_email = findViewById(R.id.login_email);
        ev_passwd = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        sign_up = findViewById(R.id.tv_signUp);
        find_pwd = findViewById(R.id.tv_find);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ev_email.getText().toString();
                String pwd = ev_passwd.getText().toString();
                if (email.equals("")||pwd.equals("")){
                    Toast.makeText(LoginActivity.this,"账号和密码不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    presenter.getData(email,pwd);
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

        find_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("请输入注册时的邮箱");    //设置对话框标题
                final EditText edit = new EditText(LoginActivity.this);
                builder.setView(edit);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (edit.getText().toString().equals("")){
                            showToast("邮箱不能为空！");
                        }else{
                            presenter.resetPwd(edit.getText().toString());
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("您取消了修改！");
                    }
                });
                builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
                AlertDialog dialog = builder.create();  //创建对话框
                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                dialog.show();
            }
        });

    }


    @Override
    public void showResult(Message result,String session) {
        Looper.prepare();
        Toast.makeText(LoginActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
        if (result.getCode().equals("103")){
            Log.d(TAG, "showResult: "+"*****************");
            SharedPreferences.Editor editor = getSharedPreferences("user_data",MODE_PRIVATE).edit();
            editor.putString("email",ev_email.getText().toString());
            editor.putString("session",session);
            editor.apply();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        Looper.loop();
    }

    @Override
    public void showResult(Message result) {
        Looper.prepare();
        showToast(result.getMsg());
        Looper.loop();
    }


}
