package com.ncbi.a3dmgame;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ncbi.a3dmgame.adapter.MainActivityViewPagerAdapter;
import com.ncbi.a3dmgame.fragment.ForumFragment;
import com.ncbi.a3dmgame.fragment.Fragment1;
import com.ncbi.a3dmgame.fragment.Fragment2;
import com.ncbi.a3dmgame.fragment.GameFragment;
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
    private RadioButton top_rg_btn[] = new RadioButton[10];
    private RadioButton bottom_rg_btn[] = new RadioButton[3];
    private int typeId = 0;
    private HorizontalScrollView horizontalScrollView_top;
    private RadioGroup radioGroup_top;
    private RadioGroup radioGroup_bottom;

    //论坛碎片
    private ForumFragment forumFragment;
    //游戏碎片
    private GameFragment gameFragment;

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
        radioGroup_bottom = (RadioGroup) findViewById(R.id.main_bottom_area_rg);
        initViewPager();
        initListener();

    }

    private void initListener() {
        //初始化所有按钮
        top_rg_btn[0] = (RadioButton) findViewById(R.id.btn0);
        //设置rg_btn0为默认选中
        top_rg_btn[0].setChecked(true);
        top_rg_btn[1] = (RadioButton) findViewById(R.id.btn1);
        top_rg_btn[2] = (RadioButton) findViewById(R.id.btn2);
        top_rg_btn[3] = (RadioButton) findViewById(R.id.btn3);
        top_rg_btn[4] = (RadioButton) findViewById(R.id.btn4);
        top_rg_btn[5] = (RadioButton) findViewById(R.id.btn5);
        top_rg_btn[6] = (RadioButton) findViewById(R.id.btn6);
        top_rg_btn[7] = (RadioButton) findViewById(R.id.btn7);
        top_rg_btn[8] = (RadioButton) findViewById(R.id.btn8);
        top_rg_btn[9] = (RadioButton) findViewById(R.id.btn9);

        bottom_rg_btn[0] = (RadioButton) findViewById(R.id.bottom_btn0);
        bottom_rg_btn[0].setChecked(true);
        bottom_rg_btn[1] = (RadioButton) findViewById(R.id.bottom_btn1);
        bottom_rg_btn[2] = (RadioButton) findViewById(R.id.bottom_btn2);

        //给顶部标题btn设置监听
        for (int i = 0; i < 10; i++) {
            top_rg_btn[i].setOnClickListener(this);
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
        fragment[0] = new Fragment2(2);
        fragment[1] = new Fragment2(151);
        fragment[2] = new Fragment2(152);
        fragment[3] = new Fragment2(153);
        fragment[4] = new Fragment2(154);
        fragment[5] = new Fragment2(196);
        fragment[6] = new Fragment2(197);
        fragment[7] = new Fragment2(199);
        fragment[8] = new Fragment2(25);
        //添加游戏和论坛的碎片初始化
        forumFragment = new ForumFragment();
        gameFragment = new GameFragment();

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
        fragmentList.add(forumFragment);
        fragmentList.add(gameFragment);

        mainFragmentViewPager = (MainFragmentViewPager) findViewById(R.id.artical_viewpager_content);
        mainFragmentViewPager.addOnPageChangeListener(this);
        mainActivityViewPagerAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainFragmentViewPager.setAdapter(mainActivityViewPagerAdapter);
    }


    @Override
    public void onClick(View view) {
        if (top_rg_btn[0].getId() == view.getId()) {
            mainFragmentViewPager.setCurrentItem(0);
            Intent intent = new Intent(this, DownLoadService.class);
            typeId = fragment0.getTypeId();
            bottom_rg_btn[0].setChecked(true);
            String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=1";
            intent.putExtra("jsonurl", jsonUrl);
            intent.putExtra("tablename", "news");
            startService(intent);
        }
        for (int i = 0; i < 9; i++) {
            if (top_rg_btn[i + 1].getId() == view.getId()) {
                mainFragmentViewPager.setCurrentItem(i + 1);
                Intent intent = new Intent(this, DownLoadService.class);
                typeId = fragment[i].getTypeId();
                String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId + "&paging=1&page=1";
                intent.putExtra("jsonurl", jsonUrl);
                intent.putExtra("tablename", "news");
                startService(intent);
                bottom_rg_btn[0].setChecked(true);
            }
        }
        //底部btn监听事件
        switch (view.getId()) {
            case R.id.bottom_btn0:
                radioGroup_top.setVisibility(View.VISIBLE);
                mainFragmentViewPager.setCurrentItem(0);
                bottom_rg_btn[0].setChecked(true);
                horizontalScrollView_top.setVisibility(View.VISIBLE);
                break;
            case R.id.bottom_btn1:
                MyLog.i("ccc", "bottom");
                //设置控件的属性为不显示且不占空间
                radioGroup_top.setVisibility(View.GONE);
//                fragmentManager = getSupportFragmentManager();
                horizontalScrollView_top.setVisibility(View.GONE);
                mainFragmentViewPager.setCurrentItem(10);
                bottom_rg_btn[1].setChecked(true);
                break;
            case R.id.bottom_btn2:
                radioGroup_top.setVisibility(View.GONE);
                horizontalScrollView_top.setVisibility(View.GONE);
                mainFragmentViewPager.setCurrentItem(11);
                bottom_rg_btn[2].setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        //判断是否为顶部的Button
        if (position <= 9) {
            bottom_rg_btn[0].setChecked(true);
            //顶部的滚动条出现移动效果
            horizontalScrollView_top.setVisibility(View.VISIBLE);
            radioGroup_top.setVisibility(View.VISIBLE);
            //设置当前pager对应的RaadioButton并设置checked状态为true
            top_rg_btn[position].setChecked(true);
            //让顶部的RadioButton随着ViewPager一起滚动
            int left = top_rg_btn[position].getLeft();
            horizontalScrollView_top.smoothScrollTo(left, 0);

            //设置网络请求下一页数据
            if (position == 0) {
                Intent intent = new Intent(this, DownLoadService.class);
                typeId = fragment0.getTypeId();

                String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=1";
                intent.putExtra("jsonurl", jsonUrl);
                intent.putExtra("tablename", "news");
                startService(intent);

            } else {
                Intent intent = new Intent(this, DownLoadService.class);
                typeId = fragment[position - 1].getTypeId();
                String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId + "&paging=1&page=1";
                intent.putExtra("jsonurl", jsonUrl);
                intent.putExtra("tablename", "news");
                startService(intent);
            }
        } else if (position > 10) {
            //底部第二个Button
            if (position - 10 == 1) {
                //设置当前pager对应的RaadioButton并设置checked状态为true
                bottom_rg_btn[1].setChecked(true);
                radioGroup_top.setVisibility(View.GONE);
                horizontalScrollView_top.setVisibility(View.GONE);
            }
            //底部第三个Button
            if (position - 10 == 2) {
                //设置当前pager对应的RaadioButton并设置checked状态为true
                bottom_rg_btn[2].setChecked(true);
                horizontalScrollView_top.setVisibility(View.GONE);
                radioGroup_top.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
