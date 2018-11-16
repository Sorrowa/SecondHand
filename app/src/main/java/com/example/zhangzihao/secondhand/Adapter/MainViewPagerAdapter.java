package com.example.zhangzihao.secondhand.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * viewPager的适配器
 */
//todo:设置传入参数。
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public MainViewPagerAdapter(FragmentManager fragmentManager,
                                ArrayList<Fragment> fragments){
        super(fragmentManager);
        fragments=new ArrayList<>();
        this.fragments.addAll(fragments);

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
