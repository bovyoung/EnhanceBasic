package com.bowyoung.enhance.ui.activity;

import android.os.Bundle;

import com.bowyoung.enhance.R;
import com.bowyoung.enhancelibrary.base.activitiy.BaseSwipeBackActivity;
import com.bowyoung.enhancelibrary.utils.ClipboardUtils;
import com.bowyoung.enhancelibrary.utils.DisplayUtils;
import com.bowyoung.enhancelibrary.utils.LogUtils;


public class MainActivity extends BaseSwipeBackActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);

        DisplayUtils.printDisplay();

        ClipboardUtils.copy("This is zero.");
        LogUtils.i(TAG, "onCreate init - " + ClipboardUtils.paste());
        ClipboardUtils.copy("This is first.");
        LogUtils.i(TAG, "onCreate add first - " + ClipboardUtils.paste());
        ClipboardUtils.copy("This is second.");
        LogUtils.i(TAG, "onCreate add second - " + ClipboardUtils.paste());
    }

}
