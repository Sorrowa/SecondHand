package com.example.zhangzihao.secondhand.Base;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public String getCurrentUser(){
        try {
            SharedPreferences pref = getSharedPreferences("user_data",MODE_PRIVATE );
            //Toast.makeText(this,pref.getString("email",null),Toast.LENGTH_SHORT).show();
            return pref.getString("email",null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
