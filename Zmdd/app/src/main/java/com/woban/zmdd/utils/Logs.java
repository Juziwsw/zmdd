package com.woban.zmdd.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/5/31.
 * @author wsw
 * Log工具类
 */
public class Logs {
    private static final boolean LOG = true;
    public static void d(String tag, String msg) {
        if(LOG) {
            Log.d(tag, msg);
        }
    }
    public static void v(String tag, String msg) {
        if(LOG) {
            Log.v(tag, msg);
        }
    }
    public static void e(String tag, String msg, Exception e) {
        if(LOG) {
           Log.e(tag, msg, e);
        }
    }
    public static void e(String tag, String msg) {
        if(LOG) {
            Log.e(tag, msg);
        }
    }
    public static void e(String msg){
        if(LOG) {
            Log.e("zmdd", msg);
        }

    }
    public static void i(String tag, String msg) {
        if(LOG) {
           Log.i(tag, msg);
        }
    }
}
