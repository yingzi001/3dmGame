package com.ncbi.a3dmgame.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ncbi.a3dmgame.R;

/**
 * Created by acer on 2016/7/6.
 */

public class MainFragmentViewPager extends ViewPager {

    public MainFragmentViewPager(Context context) {
        super(context);
    }

    public MainFragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
