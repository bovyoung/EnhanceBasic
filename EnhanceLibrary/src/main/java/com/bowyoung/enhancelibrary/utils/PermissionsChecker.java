package com.bowyoung.enhancelibrary.utils;

import android.content.pm.PackageManager;

import com.bowyoung.enhancelibrary.base.BaseAppManager;

/**
 * Created by S0S on 2016/11/3.
 */

public class PermissionsChecker {

    /**
     * 检查是否存在该权限
     * @param permissions
     * @return
     */
    public static boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (BaseAppManager.getInstance().getAppContext().checkCallingOrSelfPermission(permission) ==
                    PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }
}
