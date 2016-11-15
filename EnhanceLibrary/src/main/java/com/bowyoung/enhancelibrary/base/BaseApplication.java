package com.bowyoung.enhancelibrary.base;

import android.app.Application;

/**
 * Created by S0S on 16/7/14.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseAppManager.init(getApplicationContext());
    }
}
