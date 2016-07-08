package com.ncbi.a3dmgame;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.adapter.MainActivityViewPagerAdapter;
import com.ncbi.a3dmgame.fragment.Fragment1;
import com.ncbi.a3dmgame.fragment.MainFragmentViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private MainFragmentViewPager mainFragmentViewPager;
    private MainActivityViewPagerAdapter mainActivityViewPagerAdapter;
    private Fragment1 fragment1, fragment2, fragment3, fragment4, fragment5;
    private Fragment1 fragment6, fragment7, fragment8, fragment9, fragment0;
    private List<Fragment> fragmentList;
    private Inflater inflater = new Inflater();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    //填充主界面的ViewPager
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragment0 = new Fragment1(0);
        fragment1 = new Fragment1(1);
        fragment2 = new Fragment1(2);
        fragment3 = new Fragment1(3);
        fragment4 = new Fragment1(4);
        fragment5 = new Fragment1(5);
        fragment6 = new Fragment1(6);
        fragment7 = new Fragment1(7);
        fragment8 = new Fragment1(8);
        fragment9 = new Fragment1(9);
        fragmentList.add(fragment0);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);
        fragmentList.add(fragment7);
        fragmentList.add(fragment8);
        fragmentList.add(fragment9);

        mainFragmentViewPager = (MainFragmentViewPager) findViewById(R.id.main_viewpager_content);
        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainFragmentViewPager.setAdapter(mainActivityViewPagerAdapter);
    }


}
