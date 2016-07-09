package com.ncbi.a3dmgame.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.utils.MyLog;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by acer on 2016/7/9.
 */

public class MyCursorAdapter extends CursorAdapter {
    private Context context = null;
    private int viewResId;

    public MyCursorAdapter(Context context, Cursor c, int viewResId) {
        super(context, c);
        this.viewResId = viewResId;
    }


    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = null;
        LayoutInflater vi = null;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = (LinearLayout) vi.inflate(viewResId, parent, false);
        //v =(TextView)vi.inflate(textViewResourceId,null);
        MyLog.i("hubin", "newView" + view);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (view != null) {
            MyLog.i("bbb", "bind" + view);
            TextView tittleView = (TextView) view.findViewById(R.id.title_tv);
            TextView dateView = (TextView) view.findViewById(R.id.date_tv);
            TextView idView = (TextView) view.findViewById(R.id.id_tv);
            ImageView litpic = (ImageView) view.findViewById(R.id.litpic_iv);
            // Set the name
            String title = cursor.getString(cursor.getColumnIndex("title"));
            tittleView.setText(title);
            MyLog.i("bbb", "title" + title);
            //获取数据库中的时间并格式化
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex("senddate"))));
            String dateStr = format1.format(date);
            MyLog.i("bbb", "date:" + dateStr);
            dateView.setText(dateStr);
            idView.setText(cursor.getString(cursor.getColumnIndex("_id")));
            //获取图片的路径并填充
            String imgPath = null;
            if (cursor.getString(cursor.getColumnIndex("litpicpath")) != null) {
                imgPath = cursor.getString(cursor.getColumnIndex("litpicpath"));
                litpic.setImageDrawable(Drawable.createFromPath(imgPath));
            } else {
                litpic.setImageResource(R.drawable.product_default);
            }
        }

    }
}
