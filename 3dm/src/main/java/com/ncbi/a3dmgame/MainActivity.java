package com.ncbi.a3dmgame;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.ncbi.a3dmgame.fragment.ForumFragment;
import com.ncbi.a3dmgame.fragment.Fragment1;
import com.ncbi.a3dmgame.fragment.Fragment2;
import com.ncbi.a3dmgame.fragment.MainFragmentViewPager;
import com.ncbi.a3dmgame.service.DownLoadService;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private MainFragmentViewPager mainFragmentViewPager;
    private MainActivityViewPagerAdapter mainActivityViewPagerAdapter;
    //    private Fragment1 fragment[] = new Fragment1[10];
    private List<Fragment> fragmentList;
    private Inflater inflater = new Inflater();
    private RadioButton rg_btn[] = new RadioButton[10];
    private RadioButton bottom_rg_btn[] = new RadioButton[3];
    private int typeId = 0;


    private HorizontalScrollView horizontalScrollView_top;
    private RadioGroup radioGroup_top;

    //声明FragmentManager
    private FragmentManager fragmentManager;
    //论坛碎片
    private ForumFragment forumFragment;

    Fragment1 fragment0;
    //    Fragment2 fragment1, fragment2, fragment3, fragment4, fragment5, fragment6, fragment7, fragment8, fragment9;
    Fragment2 fragment[] = new Fragment2[9];
    int currentIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalScrollView_top = (HorizontalScrollView) findViewById(R.id.main_top_newstype_hsv);
        radioGroup_top = (RadioGroup) findViewById(R.id.main_top_newstype_rg);
        initViewPager();
        initListener();
        fragmentManager = getSupportFragmentManager();
        forumFragment = new ForumFragment();
        if (savedInstanceState==null){
            fragmentManager.beginTransaction().add(forumFragment,"forum");
        }
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
        for (int i = 0; i < 3; i++) {
            bottom_rg_btn[i].setOnClickListener(this);
        }
    }

    //填充主界面的ViewPager
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragment0 = new Fragment1(1);
//        fragment1 = new Fragment2(2);
//        fragment2 = new Fragment2(151);
//        fragment3 = new Fragment2(152);
//        fragment4 = new Fragment2(153);
//        fragment5 = new Fragment2(154);
//        fragment6 = new Fragment2(196);
//        fragment7 = new Fragment2(197);
//        fragment8 = new Fragment2(199);
//        fragment9 = new Fragment2(25);
//        fragmentList.add(fragment0);
//        fragmentList.add(fragment1);
//        fragmentList.add(fragment2);
//        fragmentList.add(fragment3);
//        fragmentList.add(fragment4);
//        fragmentList.add(fragment5);
//        fragmentList.add(fragment6);
//        fragmentList.add(fragment7);
//        fragmentList.add(fragment8);
//        fragmentList.add(fragment9);


        fragment[0] = new Fragment2(2);
        fragment[1] = new Fragment2(151);
        fragment[2] = new Fragment2(152);
        fragment[3] = new Fragment2(153);
        fragment[4] = new Fragment2(154);
        fragment[5] = new Fragment2(196);
        fragment[6] = new Fragment2(197);
        fragment[7] = new Fragment2(199);
        fragment[8] = new Fragment2(25);
        fragmentList.add(fragment0);
        fragmentList.add(fragment[0]);
        fragmentList.add(fragment[1]);
        fragmentList.add(fragment[2]);
        fragmentList.add(fragment[3]);
        fragmentList.add(fragment[4]);
        fragmentList.add(fragment[5]);
        fragmentList.add(fragment[6]);
        fragmentList.add(fragment[7]);
        fragmentList.add(fragment[8]);


        mainFragmentViewPager = (MainFragmentViewPager) findViewById(R.id.main_viewpager_content);
        mainFragmentViewPager.addOnPageChangeListener(this);
        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainFragmentViewPager.setAdapter(mainActivityViewPagerAdapter);
    }


    @Override
    public void onClick(View view) {
        if (rg_btn[0].getId() == view.getId()) {
            mainFragmentViewPager.setCurrentItem(0);
            Intent intent = new Intent(this, DownLoadService.class);
            typeId = fragment0.getTypeId();

            String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=1";
            intent.putExtra("jsonurl", jsonUrl);
            startService(intent);
//            fragment0.getSimpleCursorAdapter().notifyDataSetChanged();

        }
        for (int i = 0; i < 9; i++) {
            if (rg_btn[i + 1].getId() == view.getId()) {
                mainFragmentViewPager.setCurrentItem(i + 1);
                Intent intent = new Intent(this, DownLoadService.class);
                typeId = fragment[i].getTypeId();
                String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId + "&paging=1&page=1";
                intent.putExtra("jsonurl", jsonUrl);
                startService(intent);
//                fragment[i].getSimpleCursorAdapter().notifyDataSetChanged();
            }
        }

            //底部btn监听事件
        switch (view.getId()){
            case R.id.bottom_btn0:

                break;
            case R.id.bottom_btn1:
                MyLog.i("ccc","bottom");
//                fragmentManager = getSupportFragmentManager();
                forumFragment = new ForumFragment();
                fragmentManager.beginTransaction().add(forumFragment,"forum").commit();

                break;
            case R.id.bottom_btn2:

                break;
        }


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

        //设置网络请求下一页数据
        if (position == 0) {
            Intent intent = new Intent(this, DownLoadService.class);
            typeId = fragment0.getTypeId();

            String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=1";
            intent.putExtra("jsonurl", jsonUrl);
            startService(intent);
//            fragment0.getSimpleCursorAdapter().notifyDataSetChanged();

        }
        else {
            Intent intent = new Intent(this, DownLoadService.class);
            typeId = fragment[position - 1].getTypeId();
            String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId + "&paging=1&page=1";
            intent.putExtra("jsonurl", jsonUrl);
            startService(intent);
//                fragment[i].getSimpleCursorAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
