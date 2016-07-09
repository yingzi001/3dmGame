package com.ncbi.a3dmgame.fragment;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */

public class Fragment2 extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private View view;

    private PullToRefreshListView pullToRefreshListView;
    private MyDataBassHelper helper;
    private int typeId = 0;

    public Fragment2() {
    }

    public Fragment2(int typeId) {
        this.typeId = typeId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        return view;
    }

    private void initView() {
        view = View.inflate(getContext(), R.layout.fragment2, null);

        //获取PullToRefreshListView控件并初始化
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pullto_lv_content);
        helper = new MyDataBassHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id as _id,litpicpath,title from news", null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.newslist_item, cursor, new String[]{"title", "litpicpath"}, new int[]{R.id.title_tv, R.id.litpic_iv});
        pullToRefreshListView.setAdapter(simpleCursorAdapter);

        pullToRefreshListView.setOnRefreshListener(this);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        Service service = new Service() {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        };
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
