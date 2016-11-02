package com.bowyoung.enhancelibrary.utils;

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

    public static String timeLogic(long initTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        Date date = new Date(initTime);
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();
        long time = (now - past) / 1000;
        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) {
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return sb.append(time / 3600 + "小时前").toString();
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天").toString();
        } else if (time >= 3600 * 48 && time < 3600 * 72) {
            return sb.append("前天").toString();
        }
        return format(initTime, YEAR_MOUTH_DAY);
    }

}
