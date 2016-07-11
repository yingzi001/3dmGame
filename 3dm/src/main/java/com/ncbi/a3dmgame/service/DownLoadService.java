package com.ncbi.a3dmgame.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.utils.FileUtils;
import com.ncbi.a3dmgame.utils.HttpUtils;
import com.ncbi.a3dmgame.utils.JsonUtils;
import com.ncbi.a3dmgame.utils.JsonUtilsGame;
import com.ncbi.a3dmgame.utils.MyDataBassHelper;
import com.ncbi.a3dmgame.utils.MyLog;

import java.io.File;
import java.io.UnsupportedEncodingException;


public class DownLoadService extends Service {
    public static boolean jsonLoadFinash = false;
    private String jsonUrl;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    private MyHandler handler;
    private String tableName;
    private SQLiteDatabase db;

    public DownLoadService() {
    }

    public DownLoadService(String jsonUrl, String tableName) {
        this.tableName = tableName;
        this.jsonUrl = jsonUrl;
    }

    MyDataBassHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new MyHandler();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setTicker("你有一条新信息")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("下载完成");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (intent.getStringExtra("jsonurl") != null) {
                    tableName = intent.getStringExtra("tablename");
                    jsonUrl = intent.getStringExtra("jsonurl");
                }
                byte[] jsonByte = HttpUtils.requestToByteArray(jsonUrl);
                if (jsonByte != null) {
                    String json = null;
                    try {
                        json = new String(jsonByte, "utf-8");
                        helper = new MyDataBassHelper(getApplicationContext());
                        db = helper.getReadableDatabase();
                        //进行json解析；
                        if (tableName.equals("news")) {
                            JsonUtils.jsonToList(json, getApplicationContext());

                        } else {
                            JsonUtilsGame.jsonToList(json, getApplicationContext());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Cursor cursor = null;

                    //读取数据库的图片列
                    MyLog.i("DownLoadService", tableName);
                    cursor = db.query(tableName, new String[]{"id", "litpic"}, null, null, null, null, null);

                    while (cursor.moveToNext()) {
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String imgUrl = cursor.getString(cursor.getColumnIndex("litpic"));
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        String imgName = imgUrl.split("\\/")[imgUrl.split("\\/").length - 1];
                        MyLog.i("aaa", "分割的图片名字：" + imgName);
                        byte[] imgByte = HttpUtils.requestToByteArray(imgUrl);
                        FileUtils.SaveFileToSD("a3dmdownload", imgName, imgByte);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("litpic", path + File.separator + imgName);
                        MyLog.i("aaa", "文件路径：" + path);
                        db.execSQL("update " + tableName + " set litpicpath=? where id=?", new String[]{path + File.separator + "a3dmdownload" + File.separator + imgName, id});
                    }

                } else {
                    MyLog.i("aaa", "json解析失败");
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            builder.setContentText("数据已经下载完成");
            manager.notify(1, builder.build());
            stopSelf();
            Toast.makeText(getApplicationContext(), "下载完成，请点击查看", Toast.LENGTH_LONG).show();
        }
    }

}
