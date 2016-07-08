package com.ncbi.a3dmgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ncbi.a3dmgame.utils.MyLog;

public class DownLoadService extends Service {
    public DownLoadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.i("aaa", "Service启动了");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
