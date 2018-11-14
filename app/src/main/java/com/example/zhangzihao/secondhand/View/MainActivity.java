package com.example.zhangzihao.secondhand.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzihao.secondhand.Adapter.MainViewPagerAdapter;
import com.example.zhangzihao.secondhand.R;

import java.util.ArrayList;

/**
 * 此活动作为承载其他所有活动的基础存在
 */
public class MainActivity extends AppCompatActivity {


    private TabLayout mtabLayout;

    private ViewPager viewPager;
    //存储fragment信息
    private ArrayList<Fragment>fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();

        initViewPointer();
        initTab();
        //todo:other view init

        //todo:创建BasePresenter接口
        //todo:创建数据处理类，接受以上接口
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        viewPager=findViewById(R.id.viewPager);
        //todo:绑定具体的fragment



        //以上
        MainViewPagerAdapter adapter=new MainViewPagerAdapter(getSupportFragmentManager()
                ,fragments);
        viewPager.setAdapter(adapter);
    }

    /**
     * 所有关于tablayout的初始化工作都在此方法中
     * 初始化点击事件
     */
    private void initTab() {

        //todo:设置viewPager和tablayout的关联
        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //todo:切换viewPager
                //第二个参数为平滑滚动
                viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        //todo:ViewPager滑动绑定tabLayout
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(mtabLayout));
    }

    /**
     * 初始化布局对象
     */
    private void initViewPointer() {
        mtabLayout=findViewById(R.id.tab_main);
    }
}
