package com.ncbi.a3dmgame;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.adapter.MainActivityViewPagerAdapter;
import com.ncbi.a3dmgame.fragment.Fragment1;
import com.ncbi.a3dmgame.fragment.Fragment2;
import com.ncbi.a3dmgame.fragment.MainFragmentViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private MainFragmentViewPager mainFragmentViewPager;
    private MainActivityViewPagerAdapter mainActivityViewPagerAdapter;
    private Fragment1 fragment[] = new Fragment1[10];
    private List<Fragment> fragmentList;
    private Inflater inflater = new Inflater();
    private RadioButton rg_btn[] = new RadioButton[10];
    private RadioButton bottom_rg_btn[] = new RadioButton[3];

    private HorizontalScrollView horizontalScrollView_top;
    private RadioGroup radioGroup_top;

    Fragment1 fragment0;
    Fragment2 fragment1, fragment2, fragment3, fragment4, fragment5, fragment6, fragment7, fragment8, fragment9;
    int currentIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalScrollView_top = (HorizontalScrollView) findViewById(R.id.main_top_newstype_hsv);
        radioGroup_top = (RadioGroup) findViewById(R.id.main_top_newstype_rg);
        initViewPager();
        initListener();
    }

    private void initListener() {
        //初始化所有按钮
        rg_btn[0] = (RadioButton) findViewById(R.id.btn0);
        //设置rg_btn0为默认选中
        rg_btn[0].setChecked(true);
        rg_btn[1] = (RadioButton) findViewById(R.id.btn1);
        rg_btn[2] = (RadioButton) findViewById(R.id.btn2);
        rg_btn[3] = (RadioButton) findViewById(R.id.btn3);
        rg_btn[4] = (RadioButton) findViewById(R.id.btn4);
        rg_btn[5] = (RadioButton) findViewById(R.id.btn5);
        rg_btn[6] = (RadioButton) findViewById(R.id.btn6);
        rg_btn[7] = (RadioButton) findViewById(R.id.btn7);
        rg_btn[8] = (RadioButton) findViewById(R.id.btn8);
        rg_btn[9] = (RadioButton) findViewById(R.id.btn9);

        bottom_rg_btn[0] = (RadioButton) findViewById(R.id.bottom_btn0);
        bottom_rg_btn[0].setChecked(true);
        bottom_rg_btn[1] = (RadioButton) findViewById(R.id.bottom_btn1);
        bottom_rg_btn[2] = (RadioButton) findViewById(R.id.bottom_btn2);


        //给顶部标题btn设置监听
        for (int i = 0; i < 10; i++) {
            rg_btn[i].setOnClickListener(this);
        }

        //底部的Button设置默认状态
    }

    //填充主界面的ViewPager
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragment0 = new Fragment1(0);
        fragment1 = new Fragment2(1);
        fragment2 = new Fragment2(2);
        fragment3 = new Fragment2(3);
        fragment4 = new Fragment2(4);
        fragment5 = new Fragment2(5);
        fragment6 = new Fragment2(6);
        fragment7 = new Fragment2(7);
        fragment8 = new Fragment2(8);
        fragment9 = new Fragment2(9);
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
        mainFragmentViewPager.addOnPageChangeListener(this);
        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainFragmentViewPager.setAdapter(mainActivityViewPagerAdapter);
    }


    @Override
    public void onClick(View view) {
        for (int i = 0; i < 10; i++) {
            if (rg_btn[i].getId() == view.getId()) {
                mainFragmentViewPager.setCurrentItem(i);
            }
        }
//        if (rg_btn[1].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(1);
//        }
//        if (rg_btn[2].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(2);
//        }
//        if (rg_btn[3].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(3);
//        }
//        if (rg_btn[4].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(4);
//        }
//        if (rg_btn[5].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(5);
//        }
//        if (rg_btn[6].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(6);
//        }
//        if (rg_btn[7].getId() == view.getId()) {
//            mainFragmentViewPager.setCurrentItem(7);
//        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        //顶部的滚动条出现移动效果
        horizontalScrollView_top.setVisibility(View.VISIBLE);
        radioGroup_top.setVisibility(View.VISIBLE);
        //设置当前pager对应的RaadioButton并设置checked状态为true
        rg_btn[position].setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left = rg_btn[position].getLeft();
        horizontalScrollView_top.smoothScrollTo(left, 0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
