package com.ncbi.a3dmgame.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */

public class Fragment1 extends Fragment {
    private View view;
    private ViewPager imageViewPager;
    private MainActivityImageViewPagerAdapter mainActivityImageViewPagerAdapter;
    private List<ImageView> imageViewList;

    private PullToRefreshListView pullToRefreshListView;
    private MyDataBassHelper helper;
    private int typeId = 0;

    public Fragment1() {
    }

    public Fragment1(int typeId) {
        this.typeId = typeId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        return view;
    }

    private void initView() {
        view = View.inflate(getContext(), R.layout.fragment1, null);
        imageViewPager = (ViewPager) view.findViewById(R.id.artival_top_viewpager);
        int imageRsId[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
        //获取PullToRefreshListView控件并初始化
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pullto_lv_content);
        helper = new MyDataBassHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id as _id,litpic,title from news", null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.newslist_item, cursor, new String[]{"title", "litpic"}, new int[]{R.id.title_tv, R.id.litpic_iv});


        imageViewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViewList.add(imageView);
        }
        mainActivityImageViewPagerAdapter = new MainActivityImageViewPagerAdapter(imageViewList);
        imageViewPager.setAdapter(mainActivityImageViewPagerAdapter);
    }
}
