package com.ncbi.a3dmgame.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,color,writer,source,litpic,pubdate,senddate,mid,keywords,lastpost,scores,goodpost,badpost,voteid,notpost,description,filename,dutyadmin,tackid,mtype,weight,fby_id,game_id,feedback,typedir,typename,corank,isdefault,defaultname,namerule,namerule2,ispart,moresite,siteurl,sitepath, *
 * game表比news多出的属性
 * aid,redirecturl,templet,userip,vid,game_bbs,total,multiplayer,concept,sound,graphics,gameplay,websit,release_company,made_company,terrace,language,release_date,game_trans_name,fst,tid,game_othername1,game_othername2,zhuanti_toutiao2,zhuanti_toutiao3,zhuanti_toutiao4,
 * arcurl,typeurl,
 * <p>
 * <p>
 * "id": "3575146",
 * "typeid": "182",
 * "typeid2": "0",
 * "sortrank": "1467689662",
 * "flag": "",
 * "ismake": "1",
 * "channel": "17",
 * "arcrank": "0",
 * "click": "3469",
 * "money": "0",
 * "title": "光环6",
 * "shorttitle": "光环6",
 * "color": "",
 * "writer": "夯大力",
 * "source": "未知",
 * "litpic": "/uploads/allimg/160705/316-160F51146020-L.jpg",
 * "pubdate": "1467691680",
 * "senddate": "1467689662",
 * "mid": "316",
 * "keywords": "光环6,光环6中文版下载,截图,攻略,配置",
 * "lastpost": "0",
 * "scores": "0",
 * "goodpost": "0",
 * "badpost": "0",
 * "voteid": "0",
 * "notpost": "0",
 * "description": "《光环6（Halo 6）》专题站提供本游戏攻略,完整硬盘版下载,官网,官方网站,存档,发售日期,新闻,截图,视频,评测,评分,破解,补丁,修改器,配置,中文,汉化,秘籍等游戏资料...",
 * "filename": "halo6",
 * "dutyadmin": "316",
 * "tackid": "0",
 * "mtype": "0",
 * "weight": "265101",
 * "fby_id": "0",
 * "game_id": "0",
 * "feedback": "0",
 * "typedir": "{cmspath}/a/games",
 * "typename": "射击(FPS)",
 * "corank": "0",
 * "isdefault": "1",
 * "defaultname": "index.html",
 * "namerule": "{typedir}/{Y}/{M}{D}/{aid}.html",
 * "namerule2": "{typedir}/list_{tid}_{page}.html",
 * "ispart": "0",
 * "moresite": "0",
 * "siteurl": "",
 * "sitepath": "{cmspath}/a/games",
 * "aid": "3575146",
 * "redirecturl": "",
 * "templet": "",
 * "userip": "180.173.56.20",
 * "vid": "208303",
 * "game_bbs": "",
 * "total": "0",
 * "multiplayer": "0",
 * "concept": "0",
 * "sound": "0",
 * "graphics": "0",
 * "gameplay": "0",
 * "websit": "",
 * "release_company": "微软",
 * "made_company": "343工作室",
 * "terrace": "PC,XBOXONE",
 * "language": "简体中文,英文",
 * "release_date": "未知",
 * "game_trans_name": "Halo 6",
 * "fst": "G",
 * "tid": "第一人称射击（FPS）",
 * "game_othername1": "光晕6",
 * "game_othername2": "",
 * "zhuanti_toutiao2": "",
 * "zhuanti_toutiao3": "",
 * "zhuanti_toutiao4": "",
 * "arcurl": "http://www.3dmgame.com/games/halo6/",
 * "typeurl": "http://www.3dmgame.com/games/fps/",
 * Created by acer on 2016/6/24.
 */
public class MyDataBassHelperGame extends SQLiteOpenHelper {

    public MyDataBassHelperGame(Context context) {
        super(context, "new3dm.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
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
