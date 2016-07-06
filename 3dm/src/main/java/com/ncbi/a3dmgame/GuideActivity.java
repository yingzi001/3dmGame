package com.ncbi.a3dmgame;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ncbi.a3dmgame.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private View view1, view2, view3;
    private List<View> viewList;
    private GuideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_guide);
        viewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        view1 = inflater.inflate(R.layout.guide1_layout, null);
        view2 = inflater.inflate(R.layout.guide2_layout, null);
        view3 = inflater.inflate(R.layout.guide3_layout, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        adapter = new GuideViewPagerAdapter(viewList);
        viewPager.setAdapter(adapter);
    }
}
