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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.adapter.MyCursorAdapter;
import com.ncbi.a3dmgame.service.DownLoadService;
import com.ncbi.a3dmgame.utils.ContentActivity;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */

public class Fragment1 extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private View view;
    private ViewPager imageViewPager;
    private MainActivityImageViewPagerAdapter mainActivityImageViewPagerAdapter;
    private List<ImageView> imageViewList;
    private PullToRefreshListView pullToRefreshListView;
    private MyDataBassHelper helper;
    private int typeId;
    private MyCursorAdapter simpleCursorAdapter;
    private String jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId +
            "&paging=1&page=1";


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
        Cursor cursor = db.rawQuery("select id as _id,litpicpath,title,senddate from news", null);
        simpleCursorAdapter = new MyCursorAdapter(getContext(), cursor, R.layout.newslist_item);
        pullToRefreshListView.setAdapter(simpleCursorAdapter);
        imageViewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViewList.add(imageView);
        }
        mainActivityImageViewPagerAdapter = new MainActivityImageViewPagerAdapter(imageViewList);
        imageViewPager.setAdapter(mainActivityImageViewPagerAdapter);
        pullToRefreshListView.setOnRefreshListener(this);

        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView id_tv = (TextView) view.findViewById(R.id.id_tv);
                String id = id_tv.getText().toString();
//                    MyDataBassHelper helper = new MyDataBassHelper(getContext());
//                    SQLiteDatabase db = helper.getReadableDatabase();MyLog.i("Frament2","onItemClickListener befor"+id);
//                   Cursor contentCursor = db.rawQuery("select arcurl from news where id=?",new String[] {id+""});
//                        MyLog.i("Frament2","onItemClickListener after"+contentCursor);
//                    String contentUrl = contentCursor.getString(0);
//                MyLog.i("Frament2","onItemClickListener after"+id);
                Intent intent = new Intent(getContext(), ContentActivity.class);
                intent.putExtra("typeid",id);
                startActivity(intent);
                MyLog.i("Frament2","onItemClickListener2  "+id);

            }
        });
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    }


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

}
