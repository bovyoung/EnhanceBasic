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
    public static void copy(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) BaseAppManager.getInstance()
                .getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
        String label = text.length() > 3 ? text.substring(0, 3) : text;
        ClipData clipData = ClipData.newPlainText(label, text);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 粘贴
     *
     * @return 剪贴板内容
     */
    public static String paste() {
        ClipboardManager clipboardManager = (ClipboardManager) BaseAppManager.getInstance()
                .getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        if (clipData != null) {   //开机第一次获取会为空
            ClipData.Item cdi = clipData.getItemAt(0);
            return cdi.getText().toString();
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
