package com.example.zhangzihao.secondhand.syf.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
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

    }


    @Override
    public void showResult(Message result) {
        Looper.prepare();
        Toast.makeText(LoginActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
        if (result.getCode().equals("103")){
            Log.d(TAG, "showResult: "+"*****************");
            SharedPreferences.Editor editor = getSharedPreferences("user_data",MODE_PRIVATE).edit();
            editor.putString("email",ev_email.getText().toString());
            editor.apply();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        Looper.loop();
    }



}
