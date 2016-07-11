package com.ncbi.a3dmgame.utils.cache3;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by pavel on 2016/6/27.
 */
public class MomeryCache {

    //key  代表当前图片url地址  value  当前图片的bitmap
    private LruCache<String,Bitmap> lruCache;
    
    public MomeryCache(){
        //获得缓存大小
        //当前app能够占用的内存大小
        int maxMomery = (int)Runtime.getRuntime().maxMemory();  //32M
        //把总内存的1/8做为缓存内存
        int cacheMomery = maxMomery/8;  //4M

        lruCache = new LruCache<String,Bitmap>(cacheMomery){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //得出当前每张图片的缓存大小
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }


    //synchronized 同步
    public synchronized  void addBitmapToLruCache(String key,Bitmap bitmap){
        if(key!=null){
            if(bitmap!=null){
                lruCache.put(key,bitmap);
            }
        }
    }

    public Bitmap  getBitmapFromLruCache(String key){
        if(key!=null){
            if(lruCache.get(key)!=null){
                return lruCache.get(key);
            }
        }
        return null;
    }

    public synchronized  void removeBitmapFromLruCache(String key){
        if(key!=null){
           Bitmap bitmap =  lruCache.remove(key);
            if(bitmap!=null){
                bitmap.recycle();
            }
        }

    }

    public void clear(){
        if(lruCache.size()>0){
            lruCache.evictAll();
        }
        lruCache = null;
    }
}
