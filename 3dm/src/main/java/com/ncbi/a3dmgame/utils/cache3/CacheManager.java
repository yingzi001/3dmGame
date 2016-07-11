package com.ncbi.a3dmgame.utils.cache3;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by pavel on 2016/6/27.
 */
public class CacheManager {

    private WebCache webCache = new WebCache();
    private FileCache fileCache = new FileCache();
    private MomeryCache momeryCache = new MomeryCache();
    private Handler handler = new Handler();
    //得到缓存
    public void getCache(final String urlStr,final ImageView imageView){
        Bitmap bitmap = null;
        //首先判断内存中是否有缓存
        if(momeryCache.getBitmapFromLruCache(urlStr)!=null){
            Log.i("aaa","111");
            bitmap = momeryCache.getBitmapFromLruCache(urlStr);
            imageView.setImageBitmap(bitmap);
        }else if(fileCache.getFileFromSDcard(urlStr)!=null){
            //判断Sdcard中是否有缓存
            Log.i("aaa","222");
            bitmap = fileCache.getFileFromSDcard(urlStr);
            imageView.setImageBitmap(bitmap);
            momeryCache.addBitmapToLruCache(urlStr,bitmap);
        }else{
            Log.i("aaa","333");
            webCache.getWebCache(urlStr, new WebCache.CallBack() {
                @Override
                public void getResult(byte[] data) {
                    //得到Bitmap对象
                 //final  Bitmap  bitmap1 = BitmapFactory.decodeByteArray(data,0,data.length);
                    //压缩数据
                    final  Bitmap bitmap1 = ImageCompression.getCompressionImage(data,50,50);
                    //把压缩后的bitmap转化byte数组
                    byte[] d =  Bitmap2Bytes(bitmap1);
                    //保存到SDCard中
                    fileCache.saveFileToSDCard(d,urlStr);
                    //保存到内存中
                    momeryCache.addBitmapToLruCache(urlStr,bitmap1);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap1);
                        }
                    });
                }
            });
        }
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
    }



}
