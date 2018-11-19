package com.example.zhangzihao.secondhand.syf.signUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangzihao.secondhand.JavaBean.Message;
import com.example.zhangzihao.secondhand.JavaBean.User;
import com.example.zhangzihao.secondhand.MainActivity;
import com.example.zhangzihao.secondhand.R;
import com.example.zhangzihao.secondhand.syf.base.UserActivity;
import com.example.zhangzihao.secondhand.syf.login.LoginActivity;
import com.example.zhangzihao.secondhand.syf.presenter.SignUpPresenter;
import com.example.zhangzihao.secondhand.syf.view.SignUpView;

public class SignUpActivity extends UserActivity implements SignUpView {
    private static final String TAG = "SignUpActivity";
    private TextView tv_name,tv_password,tv_email,rpt_password;
    private CheckBox see_pwd;
    private Button sign_up;
    private RadioGroup sexGroup;
    private String sex;
    private RadioButton boy,girl;
    private android.support.v7.widget.Toolbar toolbar;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        presenter = new SignUpPresenter();
        presenter.attachView(this);
        initView();
        initSubmit();
    }

    private void initView() {
        tv_name = findViewById(R.id.sign_up_username);
        tv_password = findViewById(R.id.sign_up_password);
        tv_email = findViewById(R.id.sign_up_email);
        rpt_password = findViewById(R.id.sign_up_repeat_password);
        see_pwd=(CheckBox) findViewById(R.id.sign_up_see_password);
        sign_up=(Button) findViewById(R.id.sign_up_submit);
        sexGroup=(RadioGroup) findViewById(R.id.sign_up_sex);
        boy=(RadioButton) findViewById(R.id.sign_up_man);
        girl=(RadioButton) findViewById(R.id.sign_up_girl);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.sign_up_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        see_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //选择状态 显示明文--设置为可见的密码
                    tv_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    rpt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    //默认状态显示密码--设置文本 要一起写才能起作用  InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    tv_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    rpt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                // 通过id实例化选中的这个RadioButton
                RadioButton choice = (RadioButton) findViewById(id);
                sex=choice.getText().toString();
                Log.d(TAG, "onCheckedChanged: 性别->" + sex);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initSubmit(){
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = tv_password.getText().toString();
                String rpt_passwd = rpt_password.getText().toString();
                String name = tv_name.getText().toString();
                String email = tv_email.getText().toString();

                if (password.equals("")||name.equals("")||email.equals("")){
                    showToast("请输入所有字段！");
                }else{
                    if (!password.equals(rpt_passwd)) {
                        showToast("请确认两次密码一致！");
                    }else{
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        user.setPwd(password);
                        user.setSex(sex);
                        presenter.commitData(user);
                    }
                }
            }
        });
    }

    @Override
    public void showResult(Message result) {
        Looper.prepare();
        if (result.getCode().equals("105")){
            showToast("注册成功，请到注册邮箱激活！");
            finish();
        }else{
            showToast(result.getMsg());
        }
        Looper.loop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
