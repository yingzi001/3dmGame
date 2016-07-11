package com.ncbi.a3dmgame.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.ncbi.a3dmgame.ContentActivity;
import com.ncbi.a3dmgame.GameActivity;
import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.service.DownLoadService;
import com.ncbi.a3dmgame.utils.JsonUtils;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner gameTypre_spinner;
    private PullToRefreshGridView pullToRefreshGridView;
    private List<String> gameTypre_list;
    private String gameString = "游戏首页，动作(ACT)，射击(FPS)，角色扮演(RPG)，养成(GAL)，" +
            "益智(PUZ)，即时战略(RTS)，策略(SLG)，体育(SPG)，模拟经营(SIM)，" +
            "赛车(RAC)，冒险(AVG)，动作角色(ARPG)";
    private View view;
    private int[] typeIds = {179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192};
    private String gameUrl = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=182&paging=1&page=1";


    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView(inflater, container);
        return view;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_game, container, false);
        pullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.game_gridview);
        gameTypre_spinner = (Spinner) view.findViewById(R.id.game_spinner);
        gameTypre_list = new ArrayList<>();
        //初始化gameTypre_list
        String[] temp = gameString.split("，");
        for (String str : temp) {
            gameTypre_list.add(str);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, gameTypre_list);
        gameTypre_spinner.setOnItemSelectedListener(this);
        gameTypre_spinner.setAdapter(adapter);

        pullToRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView title_tv = (TextView) view.findViewById(R.id.title_game_tv);
                String title = title_tv.getText().toString();
//                    MyDataBassHelper helper = new MyDataBassHelper(getContext());
//                    SQLiteDatabase db = helper.getReadableDatabase();MyLog.i("Frament2","onItemClickListener befor"+title);
//                   Cursor contentCursor = db.rawQuery("select arcurl from games where title=?",new String[] {title});
//                        MyLog.i("Frament2","onItemClickListener after"+contentCursor);
//                    String contentUrl = contentCursor.getString(0);
                MyLog.i("GameFragment", "onItemClickListener after" + title);
                Intent intent = new Intent(getContext(), GameActivity.class);
                intent.putExtra("title", title);
                MyLog.i("GameFragment", "onItemClickListener2  " + title);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        MyLog.i("GameFragment", "item 被选中" + i);

        Intent intent = new Intent(getContext(), DownLoadService.class);
        intent.putExtra("jsonurl", "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=" + typeIds[i] +
                "&paging=1&page=1");
        intent.putExtra("tablename", "games");
        getActivity().startService(intent);
        MyDataBassHelper helper = new MyDataBassHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id as _id,litpicpath,title from games where typeid=?", new String[]{typeIds[i] + ""});
        SimpleCursorAdapter gameAdapter = new SimpleCursorAdapter(getContext(), R.layout.game_item, cursor, new String[]{"litpicpath", "title"},
                new int[]{R.id.cover_game_iv, R.id.title_game_tv});
        pullToRefreshGridView.setAdapter(gameAdapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
