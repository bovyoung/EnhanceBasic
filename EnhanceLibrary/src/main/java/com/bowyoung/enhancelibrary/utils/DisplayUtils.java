package com.bowyoung.enhancelibrary.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class DisplayUtils {

    private static final String TAG = "DisplayUtils";
    private static final float DOT_FIVE = 0.5f;

    public static int px2dp(float pxValue) {
        float scale = getDensity();
        return (int) (pxValue / scale + DOT_FIVE);
    }

    public static int dp2px(float dpValue) {
        float scale = getDensity();
        return (int) (dpValue * scale + DOT_FIVE);
    }

    public static int px2sp(float pxValue) {
        float fontScale = getScaledDensity();
        return (int) (pxValue / fontScale + DOT_FIVE);
    }

    public static int sp2px(float spValue) {
        float fontScale = getScaledDensity();
        return (int) (spValue * fontScale + DOT_FIVE);
    }

    /**
     * 获取屏幕分辨率
     *
     * @return
     */
    public static float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getScaledDensity() {
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static float getDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 是否横屏
     *
     * @return
     */
    public static boolean isLandscape() {
        return Resources.getSystem().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 是否竖屏
     *
     * @return
     */
    public static boolean isPortrait(Context context) {
        return Resources.getSystem().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 输出屏幕尺寸信息
     */
    public static void printDisplay() {
        LogUtils.i(TAG, "density: " + getDensity()
                + ", densityDpi: " + getDensityDpi()
                + ", scaledDensity: " + getScaledDensity()
                + ", screenWidth: " + getScreenWidth()
                + ", screenHeight: " + getScreenHeight()
                + ", xdpi: " + Resources.getSystem().getDisplayMetrics().xdpi
                + ", ydpi: " + Resources.getSystem().getDisplayMetrics().ydpi);
    }
}
