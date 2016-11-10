package com.bowyoung.enhancelibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by S0S on 16/4/28.
 */
public class BaseAppManager {

    private static final String TAG = BaseAppManager.class.getSimpleName();

    private static BaseAppManager mInstance = null;

    private Context mContext;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private static List<Activity> mActivities = new LinkedList<Activity>();

    private BaseAppManager(Context context) {
        mContext = context;
    }

    public static BaseAppManager getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("BaseAppManager has not been initialized.");
        } else {
            return mInstance;
        }
    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new BaseAppManager(context);
        }
    }

    public Context getAppContext() {
        return mContext;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }

    public synchronized void exit() {
        for (Activity activity : mActivities) {
            activity.finish();
        }
    }

    public Resources getReources() {
        return mContext.getResources();
    }

    public void runOnUIThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
