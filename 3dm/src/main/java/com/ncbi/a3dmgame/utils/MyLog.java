package com.ncbi.a3dmgame.utils;

import android.util.Log;

/**
 * Created by acer on 2016/7/7.
 */

public class MyLog {

    public static final boolean ableLog = false;//开关控制

    public static void i(String tag, String msg) {
        if (ableLog) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ableLog) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (ableLog) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (ableLog) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (ableLog) {
            Log.w(tag, msg);
        }
    }
}
