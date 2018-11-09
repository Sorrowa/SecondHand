package com.example.zhangzihao.secondhand.View;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzihao.secondhand.R;

/**
 * 此活动作为承载其他所有活动的基础存在
 */
public class MainActivity extends AppCompatActivity {


    private TabLayout mtabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPointer();
        initTab();
        //todo:other view init

        //todo:创建BasePresenter接口
        //todo:创建数据处理类，接受以上接口
        //
    }

    /**
     *
     */
    private void initTab() {
    }

    /**
     * 初始化布局对象
     */
    private void initViewPointer() {
        mtabLayout=findViewById(R.id.tab_main);
    }
}
