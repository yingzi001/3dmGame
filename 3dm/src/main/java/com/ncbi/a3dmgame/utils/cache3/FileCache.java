package com.ncbi.a3dmgame.utils.cache3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件缓存类
 * Created by pavel on 2016/6/27.
 */
public class FileCache {

    //SDcard的根目录
    private static final File SD_ROOT = Environment.getExternalStorageDirectory();
    //缓存目录
    private String cache_folder = "file_cache";
    //判断SD卡是否加载成功
    boolean isMounted = false;
    //缓存目录对象
    private static  File CACHE_FOLDER = null;


    public FileCache(){
        if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("aaa","sdcard加载出错");
        }else{
            isMounted = true;
            //创建缓存文件的目录
            CACHE_FOLDER = new File(SD_ROOT,cache_folder);
            if(!CACHE_FOLDER.exists()){
                CACHE_FOLDER.mkdirs();
            }
        }
    }

    //保存图片到缓存目录中
    //http://img20.360buyimg.com/da/jfs/t2806/86/2669597121/101576/ae0f8a6f/57708dd8N779b5cb1.jpg
    public synchronized void saveFileToSDCard(byte[] data,String urlStr){
        FileOutputStream fileOutputStream = null;
        //判断SD卡是否挂载
        if(isMounted){
            //判断缓存目录是否创建成功
            if(!CACHE_FOLDER.exists()){
                return;
            }
            //获得网络路径中图片的名字
           String fileName =  urlStr.substring(urlStr.lastIndexOf("/")+1);
            //准备保存文件
            File saveFile = new File(CACHE_FOLDER,fileName);
            try {
                fileOutputStream = new FileOutputStream(saveFile);
                fileOutputStream.write(data,0,data.length);
                Log.i("aaa","savefile="+saveFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //从SDCard中获得图片对象
    public Bitmap getFileFromSDcard(String urlStr){
        Bitmap bitmap = null;
        if(isMounted){
            if(urlStr!=null) {
                //获得网络路径中图片的名字
                String fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1);
                File getFile = new File(CACHE_FOLDER,fileName);
                if(getFile.exists()){
                  bitmap =   BitmapFactory.decodeFile(getFile.getAbsolutePath());
                }
            }
        }
      return bitmap;
    }

    //SDcard中移除某个文件
    public boolean removeFileFromSDcard(String urlStr){
        if(isMounted){
            if(urlStr!=null){
                //获得网络路径中图片的名字
                String fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1);
                File deleteFile = new File(CACHE_FOLDER,fileName);
                if(deleteFile.exists()){
                   return deleteFile.delete();
                }
            }
        }
        return false;
    }

    //从Sdcard中清空缓存文件
    public void clear(){
        if(isMounted){
           File[] allCacheFile =  CACHE_FOLDER.listFiles();
            for(File file:allCacheFile){
                file.delete();
            }
        }
    }



}
