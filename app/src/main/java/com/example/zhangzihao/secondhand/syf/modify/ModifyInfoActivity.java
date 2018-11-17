package com.example.zhangzihao.secondhand.syf.modify;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhangzihao.secondhand.R;

public class ModifyInfoActivity extends AppCompatActivity {
    LinearLayout logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        initView();
    }

    private void initView() {
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref_clean = getSharedPreferences("user_data", MODE_PRIVATE );
                pref_clean.edit().clear().commit();
                finish();
            }
        });
    }
}
