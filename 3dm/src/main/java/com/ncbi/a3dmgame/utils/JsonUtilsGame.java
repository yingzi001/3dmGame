package com.ncbi.a3dmgame.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ncbi.a3dmgame.service.DownLoadService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/6/24.
 */
public class JsonUtilsGame {
    private static MyDataBassHelper helper;

    public static List<String> jsonToList(String json, Context context) {
        List<String> result = new ArrayList<>();
        helper = new MyDataBassHelper(context);
        if (json != null) {
            try {
                JSONObject root = new JSONObject(json);
                JSONObject data = root.getJSONObject("data");
                for (int i = 0; i < 10; i++) {
                    JSONObject info = data.getJSONObject(i + "");

                    //使用常规方式进行json解析；
                    String id = info.getString("id");
                    String typeid = info.getString("typeid");
                    String typeid2 = info.getString("typeid2");
                    String sortrank = info.getString("sortrank");
                    String flag = info.getString("flag");
                    String ismake = info.getString("ismake");
                    String channel = info.getString("channel");
                    String arcrank = info.getString("arcrank");
                    String click = info.getString("click");
                    String money = info.getString("money");
                    String title = info.getString("title");
                    String shorttitle = info.getString("shorttitle");
                    String color = info.getString("color");
                    String writer = info.getString("writer");
                    String source = info.getString("source");
                    String litpic = "http://www.3dmgame.com" + info.getString("litpic");
                    result.add(litpic);
                    String pubdate = info.getString("pubdate");
                    String senddate = info.getString("senddate");
                    String mid = info.getString("mid");
                    String keywords = info.getString("keywords");
                    String lastpost = info.getString("lastpost");
                    String scores = info.getString("scores");
                    String goodpost = info.getString("goodpost");
                    String badpost = info.getString("badpost");
                    String voteid = info.getString("voteid");
                    String notpost = info.getString("notpost");
                    String description = info.getString("description");
                    String filename = info.getString("filename");
                    String dutyadmin = info.getString("dutyadmin");
                    String tackid = info.getString("tackid");
                    String mtype = info.getString("mtype");
                    String weight = info.getString("weight");
                    String fby_id = info.getString("fby_id");
                    String game_id = info.getString("game_id");
                    String feedback = info.getString("feedback");
                    String typedir = info.getString("typedir");
                    String typename = info.getString("typename");
                    String corank = info.getString("corank");
                    String isdefault = info.getString("isdefault");
                    String defaultname = info.getString("defaultname");
                    String namerule = info.getString("namerule");
                    String namerule2 = info.getString("namerule2");
                    String ispart = info.getString("ispart");
                    String moresite = info.getString("moresite");
                    String siteurl = info.getString("siteurl");
                    String sitepath = info.getString("sitepath");
                    //aid,redirecturl,templet,userip,vid,game_bbs,total,multiplayer,concept,sound,
                    // graphics,gameplay,websit,release_company,made_company,terrace,language,
                    // release_date,game_trans_name,fst,tid,game_othername1,game_othername2,
                    // zhuanti_toutiao2,zhuanti_toutiao3,zhuanti_toutiao4,
                    //  game增加的属性
                    String aid = info.getString("aid");
                    String redirecturl = info.getString("redirecturl");
                    String templet = info.getString("templet");
                    String userip = info.getString("userip");
                    String vid = info.getString("vid");
                    String game_bbs = info.getString("game_bbs");
                    String total = info.getString("total");
                    String multiplayer = info.getString("multiplayer");
                    String concept = info.getString("concept");
                    String sound = info.getString("sound");
                    String graphics = info.getString("graphics");
                    String gameplay = info.getString("gameplay");
                    String websit = info.getString("websit");
                    String release_company = info.getString("release_company");
                    String made_company = info.getString("made_company");
                    String terrace = info.getString("terrace");
                    String language = info.getString("language");
                    String release_date = info.getString("release_date");
                    String game_trans_name = info.getString("game_trans_name");
                    String fst = info.getString("fst");
                    String tid = info.getString("tid");
                    String game_othername1 = info.getString("game_othername1");
                    String game_othername2 = info.getString("game_othername2");
                    String zhuanti_toutiao2 = info.getString("zhuanti_toutiao2");
                    String zhuanti_toutiao3 = info.getString("zhuanti_toutiao3");
                    String zhuanti_toutiao4 = info.getString("zhuanti_toutiao4");


                    String arcurl = info.getString("arcurl");
                    String typeurl = info.getString("typeurl");

                    //获取数据库，并将数据写入到数据库当中；
                    try {
                        SQLiteDatabase database = helper.getReadableDatabase();
                        database.execSQL("insert into games (id ,typeid,typeid2,sortrank,flag,ismake," +
                                        "channel,arcrank,click,money,title,shorttitle,color,writer,source," +
                                        "litpic,pubdate,senddate,mid,keywords,lastpost,scores,goodpost," +
                                        "badpost,voteid,notpost,description,filename,dutyadmin,tackid," +
                                        "mtype,weight,fby_id,game_id,feedback,typedir,typename,corank," +
                                        "isdefault,defaultname,namerule,namerule2,ispart,moresite,siteurl,sitepath," +

                                        "aid,redirecturl,templet,userip,vid,game_bbs,total,multiplayer,concept,sound," +
                                        "graphics,gameplay,websit,release_company,made_company,terrace,language," +
                                        " release_date,game_trans_name,fst,tid,game_othername1,game_othername2," +
                                        "zhuanti_toutiao2,zhuanti_toutiao3,zhuanti_toutiao4," +


                                        "arcurl,typeurl) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                                        ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                , new Object[]{id, typeid, typeid2, sortrank, flag, ismake, channel, arcrank, click,
                                        money, title, shorttitle, color, writer, source, litpic, pubdate, senddate,
                                        mid, keywords, lastpost, scores, goodpost, badpost, voteid, notpost, description,
                                        filename, dutyadmin, tackid, mtype, weight, fby_id, game_id, feedback, typedir, typename,
                                        corank, isdefault, defaultname, namerule, namerule2, ispart, moresite, siteurl, sitepath,
                                        aid, redirecturl, templet, userip, vid, game_bbs, total, multiplayer,
                                        concept, sound, graphics, gameplay, websit, release_company, made_company, terrace, language,
                                        release_date, game_trans_name, fst, tid, game_othername1, game_othername2,
                                        zhuanti_toutiao2, zhuanti_toutiao3, zhuanti_toutiao4, arcurl, typeurl});
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

//                    ContentValues contentValues = new ContentValues();
//                    contentValues.put("id", id);
//                    contentValues.put("typeid", typeid);
//                    contentValues.put("typeid2", typeid2);
//                    contentValues.put("sortrank", sortrank);
//                    contentValues.put("flag", flag);
//                    contentValues.put("ismake", ismake);
//                    contentValues.put("channel", channel);
//                    contentValues.put("arcrank", arcrank);
//                    contentValues.put("click", click);
//                    contentValues.put("money", money);
//                    contentValues.put("title", title);
//                    contentValues.put("shorttitle", shorttitle);
//                    contentValues.put("color", color);
//                    contentValues.put("writer", writer);
//                    contentValues.put("source", source);
//                    contentValues.put("litpic", litpic);
//                    contentValues.put("pubdate", pubdate);
//                    contentValues.put("senddate", senddate);
//                    contentValues.put("mid", mid);
//                    contentValues.put("keywords", keywords);
//                    contentValues.put("lastpost", lastpost);
//                    contentValues.put("scores", scores);
//                    contentValues.put("goodpost", goodpost);
//                    contentValues.put("badpost", badpost);
//                    contentValues.put("voteid", voteid);
//                    contentValues.put("notpost", notpost);
//                    contentValues.put("description", description);
//                    contentValues.put("filename", filename);
//                    contentValues.put("dutyadmin", dutyadmin);
//                    contentValues.put("tackid", tackid);
//                    contentValues.put("mtype", mtype);
//                    contentValues.put("weight", weight);
//                    contentValues.put("fby_id", fby_id);
//                    contentValues.put("game_id", game_id);
//                    contentValues.put("feedback", feedback);
//                    contentValues.put("typedir", typedir);
//                    contentValues.put("typename", typename);
//                    contentValues.put("corank", corank);
//                    contentValues.put("isdefault", isdefault);
//                    contentValues.put("defaultname", defaultname);
//                    contentValues.put("namerule", namerule);
//                    contentValues.put("namerule2", namerule2);
//                    contentValues.put("ispart", ispart);
//                    contentValues.put("moresite", moresite);
//                    contentValues.put("siteurl", siteurl);
//                    contentValues.put("sitepath", sitepath);
//                    contentValues.put("arcurl", arcurl);
//                    contentValues.put("typeurl", typeurl);
//
//                    database.insert("news", "id,typeid,typeid2,sortrank,flag,ismake," +
//                            "channel,arcrank,click,money,title,shorttitle,color,writer,source," +
//                            "litpic,pubdate,senddate,mid,keywords,lastpost,scores,goodpost," +
//                            "badpost,voteid,notpost,description,filename,dutyadmin,tackid," +
//                            "mtype,weight,fby_id,game_id,feedback,typedir,typename,corank," +
//                            "isdefault,defaultname,namerule,namerule2,ispart,moresite,siteurl," +
//                            "sitepath,arcurl,typeurl", contentValues);
                    MyLog.i("JsonUtilsGame", "game解析成功" + title);
                    DownLoadService.jsonLoadFinash = true;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
