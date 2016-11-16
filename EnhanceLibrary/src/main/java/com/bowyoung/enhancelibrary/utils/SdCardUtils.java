package com.bowyoung.enhancelibrary.utils;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created by zhangboo on 2016/11/16.
 * <p>
 * Desc: SdCard 工具类
 */

public class SDCardUtils {

    /**
     * 是否存在 SDCard
     *
     * @return
     */
    public static boolean hasSDCard() {
        boolean hasSDCard = false;
        if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
            hasSDCard = true;
        } else {
            hasSDCard = false;
        }
        return hasSDCard;
    }

    public static String getSDCardPath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }
        return "/sdcard/";
    }

    public static boolean isSDCardWrite() {
        return Environment.getExternalStorageDirectory().canWrite();
    }

    public static boolean hasSDCardAndCanWrite() {
        return hasSDCard() && isSDCardWrite();
    }

    public static long getSDCardAvailableBlocks() {
        if (hasSDCardAndCanWrite()) {
            String path = getSDCardPath();
            if (path != null) {
                StatFs statFs = new StatFs(path);
                long blockSize = statFs.getBlockSizeLong();
                long availaBlock = statFs.getAvailableBlocksLong();
                return blockSize * availaBlock;
            }
        }
        return 0;
    }
}
