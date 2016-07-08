package com.ncbi.a3dmgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ncbi.a3dmgame.adapter.GuideViewPagerAdapter;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private View view1, view2, view3;
    private List<View> viewList;
    private GuideViewPagerAdapter adapter;
    private int curentPosition = 0;
    private int lastPosition;
    private ImageView[] guide_dot_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        //初始化guide_dot ImageView;
        guide_dot_iv = new ImageView[3];
        guide_dot_iv[0] = (ImageView) findViewById(R.id.guide_dot0);
        guide_dot_iv[1] = (ImageView) findViewById(R.id.guide_dot1);
        guide_dot_iv[2] = (ImageView) findViewById(R.id.guide_dot2);
        //初始化ViewPager;
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
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        MyLog.i("aaa", "onPageScrollStateChanged   positionOffsetPixels:" + positionOffsetPixels + "   positionOffset" + positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        MyLog.i("aaa", "onPageSelected");
        guide_dot_iv[curentPosition].setImageResource(R.drawable.dot_white);
        guide_dot_iv[position].setImageResource(R.drawable.dot_dark);
        curentPosition = position;
        if (position == 2) {
            Button button = (Button) view3.findViewById(R.id.guide_to_mainactivity_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentMain = new Intent(GuideActivity.this, MainActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("isFirstOpen", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirstOpen", true);
                    editor.commit();
                    startActivity(intentMain);
                    finish();
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (curentPosition == 2 && state == ViewPager.SCROLL_STATE_DRAGGING) {
            MyLog.i("aaa", "onPageScrollStateChanged");
            Intent intentMain = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("isFirstOpen", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstOpen", true);
            editor.commit();
            startActivity(intentMain);
            finish();
        }
    }

}
