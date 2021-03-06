package com.bowyoung.enhancelibrary.base.activitiy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bowyoung.enhancelibrary.base.BaseAppManager;

import butterknife.ButterKnife;

/**
 * Created by S0S on 16/4/28.
 */
public abstract class BaseEnhanceActivity extends AppCompatActivity {

    protected Context mContext = null;
    protected View mView;

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract View getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        BaseAppManager.getInstance().addActivity(this);
        if (getContentView() != null) {
            mView = getContentView();
            setContentView(mView);
            ButterKnife.inject(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView");
        }
    }

    protected View getView(){
        return mView;
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

    public void startAcitivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(mContext, clazz);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void finish() {
        BaseAppManager.getInstance().removeActivity(this);
        super.finish();
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
