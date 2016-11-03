package com.bowyoung.enhancelibrary.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.bowyoung.enhancelibrary.base.BaseAppManager;

/**
 * Created by S0S on 16/7/27.
 */
public class ProcessHelper {

    private static ProcessHelper mInstance = null;
    private ActivityManager mActivityManager;

    private ProcessHelper() {
        mActivityManager = (ActivityManager) BaseAppManager.getInstance().getAppContext().
                getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static ProcessHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ProcessHelper();
        }
        return mInstance;
    }

    public void killProcess(int pid) {
        Process.killProcess(pid);
    }

    public void killProcess(String processName) {
        int pid = getProcessPid(processName);
        killProcess(pid);
    }

    /**
     * According to the process name return Pid
     *
     * @param processName
     * @return If found the return process Pid, otherwise return -1
     */
    public int getProcessPid(String processName) {
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.processName.equals(processName)) {
                return appProcess.pid;
            }
        }
        return -1;
    }

    /**
     * According to the process of Pid return process name
     *
     * @param pid
     * @return If found the return process name, otherwise return null
     */
    public String getProcessName(int pid) {
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }
}
