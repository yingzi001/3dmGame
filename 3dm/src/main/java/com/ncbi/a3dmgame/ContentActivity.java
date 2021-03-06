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

import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

public class ContentActivity extends AppCompatActivity {
    private WebView contentWeb;
    private String forumUrl = "http://www.baidu.com/";
    private WebSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        //初始化新闻内容控件
        contentWeb = (WebView) findViewById(R.id.content_web);
        settings = contentWeb.getSettings();
        settings.setBuiltInZoomControls(true);
        Intent intent = getIntent();
        String id = intent.getStringExtra("typeid");
        MyDataBassHelper helper = new MyDataBassHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        MyLog.i("Frament2","onItemClickListener befor"+id);
        Cursor contentCursor = db.rawQuery("select arcurl from news where id=?",new String[] {id+""});
        MyLog.i("Frament2","onItemClickListener after"+contentCursor);
        if (contentCursor.moveToNext()) {
            forumUrl = contentCursor.getString(0);
        }
        MyLog.i("Frament2","onItemClickListener after"+id);
        MyLog.i("ContentActivity",forumUrl);
        contentWeb.loadUrl(forumUrl);
        if (forumUrl!=null) {
            contentWeb.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(forumUrl);
                    MyLog.i("ContentActivity","请求成功");
                    return true;
                }
            });
        }
    }
}
