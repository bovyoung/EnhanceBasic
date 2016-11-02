package com.bowyoung.enhancelibrary;

import android.app.Application;

import com.bowyoung.enhancelibrary.base.BaseAppManager;
import com.bowyoung.enhancelibrary.skin.resource.SkinContextWrapper;

/**
 * Created by S0S on 16/7/14.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseAppManager.init(new SkinContextWrapper(getApplicationContext()));
    }
}
