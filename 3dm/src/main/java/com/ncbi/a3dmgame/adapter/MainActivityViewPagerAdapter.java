package com.ncbi.a3dmgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */

public class MainActivityViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> viewList;

    public MainActivityViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainActivityViewPagerAdapter(FragmentManager fm, List<Fragment> viewList) {
        super(fm);
        this.viewList = viewList;
    }


    @Override
    public Fragment getItem(int position) {
        return viewList.get(position);
    }


    @Override
    public int getCount() {
        return viewList.size();
    }
}
