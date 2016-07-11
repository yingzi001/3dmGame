package com.ncbi.a3dmgame.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.print.PrintHelper;
import android.support.v4.view.ViewPager;
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
import com.ncbi.a3dmgame.ContentActivity;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class Fragment1 extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView>, ViewPager.OnPageChangeListener {
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

    private Messenger messenger;

    private int currentInsex = 0;


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
        final Cursor cursor = db.rawQuery("select id as _id,litpicpath,title,senddate from news", null);
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
        imageViewPager.addOnPageChangeListener(this);

        messenger = new Messenger(new Myhandler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                imageViewPager.setCurrentItem(msg.arg1);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        Thread.sleep(3000);
                        MyLog.i("imageViewPager", "滑动前" + currentInsex);
                        Message msg = Message.obtain();
                        msg.arg1 = currentInsex;
                        try {
                            messenger.send(msg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        currentInsex++;
                        MyLog.i("imageViewPager", "滑动后" + currentInsex);
                        if (currentInsex == 3f) {
                            currentInsex = 0;
                        }


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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
                intent.putExtra("typeid", id);
                startActivity(intent);
                MyLog.i("Frament2", "onItemClickListener2  " + id);


            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        simpleCursorAdapter.notifyDataSetChanged();
        pullToRefreshListView.onRefreshComplete();
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

    //imageViewPagerde的滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

}
