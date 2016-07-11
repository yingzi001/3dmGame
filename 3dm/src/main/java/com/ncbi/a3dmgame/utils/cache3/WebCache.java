package com.ncbi.a3dmgame.utils.cache3;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存类
 * Created by pavel on 2016/6/27.
 */
public class WebCache {

    public void getWebCache(final String urlStr,final CallBack callBack){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] data=  getWebFile(urlStr);
                    callBack.getResult(data);
                    // bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public byte[] getWebFile(String urlStr)throws  Exception{
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        httpURLConnection.setRequestMethod("GET");
        //设置输入流
        httpURLConnection.setDoInput(true);
        //连接
        httpURLConnection.connect();
        //连接成功，
        Log.i("aaa","code="+httpURLConnection.getResponseCode());
        if(httpURLConnection.getResponseCode()==200){
            InputStream inputStream =  httpURLConnection.getInputStream();
            //创建输出流的对象
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] data = new byte[100];
            int len = -1;
            while((len=inputStream.read(data))!=-1){
                byteArrayOutputStream.write(data,0,len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            byte[] result =  byteArrayOutputStream.toByteArray();
            return result;

        }
        return null;
    }

    public interface  CallBack{
        public void getResult(byte[] data);
    }


}
