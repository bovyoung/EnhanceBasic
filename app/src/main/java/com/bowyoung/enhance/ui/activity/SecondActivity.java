package com.bowyoung.enhance.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import com.bowyoung.enhance.R;
import com.bowyoung.enhancelibrary.base.activitiy.BaseSwipeBackActivity;


/**
 * Created by S0S on 16/7/15.
 */
public class SecondActivity extends BaseSwipeBackActivity {

    private Button mBtnSwitch;

    @Override
    protected int getContentViewID() {
        return R.layout.activity_second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
