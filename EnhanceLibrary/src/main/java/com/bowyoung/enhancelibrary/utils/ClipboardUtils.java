package com.bowyoung.enhancelibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.bowyoung.enhancelibrary.base.BaseAppManager;

/**
 * Created by zhangboo on 2016/11/15.
 * <p>
 * Desc: 剪切板操作工具类
 */

public class ClipboardUtils {

    private static final String TAG = "ClipboardUtils";

    /**
     * 复制
     *
     * @param text
     */
    public static boolean copy(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) BaseAppManager.getInstance()
                .getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
        String label = text.length() > 3 ? text.substring(0, 3) : text;
        ClipData clipData = ClipData.newPlainText(label, text);
        try {   //在 TV 端此处会抛空指针异常
            clipboardManager.setPrimaryClip(clipData);
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
            return false;
        }
        return true;
    }

    /**
     * 粘贴
     *
     * @return 剪贴板内容
     */
    public static String paste() {
        ClipboardManager clipboardManager = (ClipboardManager) BaseAppManager.getInstance()
                .getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
        try {
            ClipData clipData = clipboardManager.getPrimaryClip();      //TV 端此处会抛空指针异常
            if (clipData != null) {   //开机第一次获取会为空
                ClipData.Item cdi = clipData.getItemAt(0);
                return cdi.getText().toString();
            }
        } catch (Exception e) {
            LogUtils.i(TAG, e);
        }
        return "";
    }

    /**
     * 清空剪贴板
     */
    public static void clear() {
        copy("");
    }

}
