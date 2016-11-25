package com.bowyoung.enhancelibrary.utils;

import android.util.Log;

import com.bowyoung.enhancelibrary.config.AppConfig;

/**
 * Created by S0S on 16/5/6.
 */
public class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();
    public static boolean mIsDebug = AppConfig.IS_DEBUG;

    public static void setDebug(boolean isDebug) {
        if (isDebug) {
            i(TAG, "log is open");
        } else {
            i(TAG, "log is close");
        }
        mIsDebug = isDebug;
    }

    public static void d(String tag, Object object) {
        if (mIsDebug) {
            Log.d(tag, object.toString());
        }
    }

    public static void d(String tag, Object object, Throwable tr) {
        if (mIsDebug) {
            Log.d(tag, object.toString(), tr);
        }
    }

    public static void e(String tag, Object object) {
        if (mIsDebug) {
            Log.e(tag, object.toString());
        }
    }

    public static void e(String tag, Object object, Throwable tr) {
        if (mIsDebug) {
            Log.e(tag, object.toString(), tr);
        }
    }

    public static void i(String tag, Object object) {
        if (mIsDebug) {
            Log.i(tag, object.toString());
        }
    }

    public static void w(String tag, Object object) {
        if (mIsDebug) {
            Log.w(tag, object.toString());
        }
    }

    public static void v(String tag, Object object) {
        if (mIsDebug) {
            Log.v(tag, object.toString());
        }
    }

}
