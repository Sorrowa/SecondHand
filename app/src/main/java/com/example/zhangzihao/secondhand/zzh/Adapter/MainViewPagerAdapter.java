package com.example.zhangzihao.secondhand.zzh.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        this.fragments=fragments;

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
