package com.ncbi.a3dmgame.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.adapter.MyCursorAdapter;
import com.ncbi.a3dmgame.ContentActivity;
import com.ncbi.a3dmgame.service.DownLoadService;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

/**
 * Created by acer on 2016/7/6.
 */

public class Fragment2 extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView> {
    private View view;

    private PullToRefreshListView pullToRefreshListView;
    private MyDataBassHelper helper;
    private int typeId;
    private String jsonUrl;
    //http://www.3dmgame.com/sitemap/api.php?id=<文章ID>&typeid=<分类ID>

    private MyCursorAdapter simpleCursorAdapter;


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

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
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pullto_lv_content2);
        helper = new MyDataBassHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        final Cursor cursor = db.rawQuery("select id as _id,litpicpath,title,senddate from news where typeid=?", new String[]{typeId + ""});
        if (cursor != null) {
            simpleCursorAdapter = new MyCursorAdapter(getContext(), cursor, R.layout.newslist_item);
            pullToRefreshListView.setAdapter(simpleCursorAdapter);
            pullToRefreshListView.setOnRefreshListener(this);
        }

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
        jsonUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeId +
                "&paging=1&page=1";
        Intent intent = new Intent(getContext(), DownLoadService.class);
        getActivity().startService(intent);
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
