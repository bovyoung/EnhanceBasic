package com.bowlun.s0slibrary.utils;

import android.util.Log;

/**
 * Created by S0S on 16/5/6.
 */
public class LogUtils {

    private static final String TAG = "LogUtils";
    public static boolean mIsDebug = true;

    public static void setDebug(boolean isDebug) {
        if (isDebug) {
            i("LogUtils", "log is open");
        } else {
            i("LogUtils", "log is close");
        }
        mIsDebug = isDebug;
    }

    public static void d(String tag, Object object) {
        if (mIsDebug)
            Log.d(tag, object.toString());
    }

    public static void d(String tag, Object object, Throwable tr) {
        if (mIsDebug)
            Log.d(tag, object.toString(), tr);
    }

    public static void e(String tag, Object object) {
        if (mIsDebug)
            Log.e(tag, object.toString());
    }

    public static void e(String tag, Object object, Throwable tr) {
        if (mIsDebug)
            Log.e(tag, object.toString(), tr);
    }

    public static void i(String tag, Object object) {
        if (mIsDebug)
            Log.i(tag, object.toString());
    }

}
