package com.bowyoung.enhancelibrary.utils;

import android.content.res.Resources;
import android.os.SystemClock;

import com.bowyoung.enhancelibrary.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {

    public final static String YEAR_MOUTH_DAY = "yyyy-MM-dd";
    public final static String HOUR_MINUTE_SECOND = "HH:mm:ss";

    public static String format(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String formatAll(long time) {
        return format(time, YEAR_MOUTH_DAY + " " + HOUR_MINUTE_SECOND);
    }

    public static String timeLogic(long time) {
        Resources res = Resources.getSystem();
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        Date date = new Date(time);
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();
        long offsetTime = (now - past) / 1000;
        StringBuilder sb = new StringBuilder();
        if (offsetTime > 0 && offsetTime < 60) {
            return sb.append(offsetTime + res.getString(R.string.before_seconds)).toString();
        } else if (offsetTime > 60 && offsetTime < 3600) {
            return sb.append(offsetTime / 60 + res.getString(R.string.before_minutes)).toString();
        } else if (offsetTime >= 3600 && offsetTime < 3600 * 24) {
            return sb.append(offsetTime / 3600 + res.getString(R.string.before_hours)).toString();
        } else if (offsetTime >= 3600 * 24 && offsetTime < 3600 * 48) {
            return sb.append(res.getString(R.string.yesterday)).toString();
        } else if (offsetTime >= 3600 * 48 && offsetTime < 3600 * 72) {
            return sb.append(res.getString(R.string.before_yesterday)).toString();
        }
        return format(time, YEAR_MOUTH_DAY);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前线程运行的时长
     *
     * @return
     */
    public static long getCurrentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    /**
     * 获取系统启动以来经过的毫秒
     *
     * @return
     */
    public static long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

}
