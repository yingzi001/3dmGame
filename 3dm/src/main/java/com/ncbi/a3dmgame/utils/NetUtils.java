package com.ncbi.a3dmgame.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ncbi.a3dmgame.WelcomeActivity;

/**
 * Created by acer on 2016/7/5.
 */

public class NetUtils {
    public static final int NO_NET = -1;
    private Activity activity;

    public NetUtils(Activity activity) {
        this.activity = activity;
    }

    public static ConnectivityManager getManager(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }

    public ConnectivityManager getManager() {
        Activity activity = this.activity;
        ConnectivityManager connectivityManager = (ConnectivityManager)
                activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }

    public static boolean netOk(Activity activity) {
        //获取网络连接管理器
        ConnectivityManager connectivityManager = getManager(activity);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    public boolean netOk() {
        Activity activity = this.activity;
        //获取网络连接管理器
        ConnectivityManager connectivityManager = getManager(activity);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    public static int getNetType(Activity activity) {
        if (netOk(activity)) {
            ConnectivityManager connectivityManager = getManager(activity);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo.getType();
        }
        return NO_NET;
    }

    public int getNetType() {
        Activity activity = this.activity;
        if (netOk(activity)) {
            ConnectivityManager connectivityManager = getManager(activity);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo.getType();
        }
        return NO_NET;
    }
}
