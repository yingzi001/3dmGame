package com.ncbi.a3dmgame.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,color,writer,source,litpic,pubdate,senddate,mid,keywords,lastpost,scores,goodpost,badpost,voteid,notpost,description,filename,dutyadmin,tackid,mtype,weight,fby_id,game_id,feedback,typedir,typename,corank,isdefault,defaultname,namerule,namerule2,ispart,moresite,siteurl,sitepath,arcurl,typeurl,
 */

/**
 * Created by acer on 2016/6/24.
 */
public class MyDataBassHelper extends SQLiteOpenHelper {

    public MyDataBassHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists news (id varchar(40) primary key,typeid varchar(40)," +
                "typeid2 varchar(40),sortrank varchar(40),flag varchar(40),ismake varchar(40)," +
                "channel varchar(40),arcrank varchar(40),click varchar(40),money varchar(40)," +
                "title varchar(40),shorttitle varchar(40),color varchar(40),writer varchar(40)," +
                "source varchar(40),litpic varchar(40),litpicpath varchar(40),pubdate varchar(40),senddate varchar(40)," +
                "mid varchar(40),keywords varchar(40),lastpost varchar(40),scores varchar(40)," +
                "goodpost varchar(40),badpost varchar(40),voteid varchar(40),notpost varchar(40)," +
                "description varchar(40),filename varchar(40),dutyadmin varchar(40),tackid varchar(40)," +
                "mtype varchar(40),weight varchar(40),fby_id varchar(40),game_id varchar(40)," +
                "feedback varchar(40),typedir varchar(40),typename varchar(40),corank varchar(40)," +
                "isdefault varchar(40),defaultname varchar(40),namerule varchar(40),namerule2 varchar(40)," +
                "ispart varchar(40),moresite varchar(40),siteurl varchar(40),sitepath varchar(40)," +
                "arcurl varchar(40),typeurl varchar(40))");

        sqLiteDatabase.execSQL("create table if not exists games (id varchar(40) primary key,typeid varchar(40)," +
                "typeid2 varchar(40),sortrank varchar(40),flag varchar(40),ismake varchar(40)," +
                "channel varchar(40),arcrank varchar(40),click varchar(40),money varchar(40)," +
                "title varchar(40),shorttitle varchar(40),color varchar(40),writer varchar(40)," +
                "source varchar(40),litpic varchar(40),litpicpath varchar(40),pubdate varchar(40),senddate varchar(40)," +
                "mid varchar(40),keywords varchar(40),lastpost varchar(40),scores varchar(40)," +
                "goodpost varchar(40),badpost varchar(40),voteid varchar(40),notpost varchar(40)," +
                "description varchar(40),filename varchar(40),dutyadmin varchar(40),tackid varchar(40)," +
                "mtype varchar(40),weight varchar(40),fby_id varchar(40),game_id varchar(40)," +
                "feedback varchar(40),typedir varchar(40),typename varchar(40),corank varchar(40)," +
                "isdefault varchar(40),defaultname varchar(40),namerule varchar(40),namerule2 varchar(40)," +
                "ispart varchar(40),moresite varchar(40),siteurl varchar(40),sitepath varchar(40)," +

                "aid varchar(40),redirecturl varchar(40),templet varchar(40),userip varchar(40),vid varchar(40),game_bbs varchar(40),total varchar(40)," +
                "multiplayer varchar(40),concept varchar(40),sound varchar(40),graphics varchar(40),gameplay varchar(40),websit varchar(40)," +
                "release_company varchar(40),made_company varchar(40),terrace varchar(40),language varchar(40),release_date varchar(40)," +
                "game_trans_name varchar(40),fst varchar(40),tid varchar(40),game_othername1 varchar(40)," +
                "game_othername2 varchar(40),zhuanti_toutiao2 varchar(40),zhuanti_toutiao3 varchar(40),zhuanti_toutiao4 varchar(40),"

                + "arcurl varchar(40),typeurl varchar(40))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
