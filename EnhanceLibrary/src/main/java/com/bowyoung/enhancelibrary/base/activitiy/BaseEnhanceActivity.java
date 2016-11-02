package com.bowyoung.enhancelibrary.base.activitiy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.bowyoung.enhancelibrary.base.BaseAppManager;

import butterknife.ButterKnife;

/**
 * Created by S0S on 16/4/28.
 */
public abstract class BaseEnhanceActivity extends AppCompatActivity {

    protected Context mContext = null;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewID();

    /**
     * find views
     */
    protected abstract void findViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        BaseAppManager.getInstance().addActivity(this);
        initScreenInformation();
        if (getContentViewID() != 0) {
            setContentView(getContentViewID());
            ButterKnife.inject(this);
            findViews();
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
    }

    /**
     * Init screen information
     */
    private void initScreenInformation() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }

    public void startActivityAndFinish(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        finish();
    }

    public void startActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
