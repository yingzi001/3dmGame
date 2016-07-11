package com.ncbi.a3dmgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

public class GameActivity extends AppCompatActivity {
    private WebView gameWeb;
    private String forumUrl = "http://www.baidu.com/";
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //初始化新闻内容控件
        gameWeb = (WebView) findViewById(R.id.game_web);
        settings = gameWeb.getSettings();
        settings.setBuiltInZoomControls(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        MyDataBassHelper helper = new MyDataBassHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        MyLog.i("GameActivity", "onItemClickListener befor" + title);
        Cursor contentCursor = db.rawQuery("select arcurl from games where title=?", new String[]{title});
        MyLog.i("GameActivity", "onItemClickListener after" + contentCursor);
        if (contentCursor.moveToNext()) {
            forumUrl = contentCursor.getString(0);
        }
        MyLog.i("GameActivity", "onItemClickListener after" + forumUrl);
        gameWeb.loadUrl(forumUrl);
        if (forumUrl != null) {
            gameWeb.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(forumUrl);
                    MyLog.i("ContentActivity", "请求成功");
                    return true;
                }
            });
        }
    }
}
