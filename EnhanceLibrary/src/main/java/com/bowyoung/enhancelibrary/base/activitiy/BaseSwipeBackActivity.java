package com.bowyoung.enhancelibrary.base.activitiy;

import android.os.Bundle;
import android.view.View;

import com.bowyoung.enhancelibrary.libs.swipeback.SwipeBackActivityBase;
import com.bowyoung.enhancelibrary.libs.swipeback.SwipeBackActivityHelper;
import com.bowyoung.enhancelibrary.libs.swipeback.SwipeBackLayout;
import com.bowyoung.enhancelibrary.libs.swipeback.SwipeBackUtils;


public abstract class BaseSwipeBackActivity extends BaseEnhanceActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        mSwipeBackLayout = getSwipeBackLayout();
        setSwipeGesture(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    public void setSwipeGesture(int direction) {
        mSwipeBackLayout.setEdgeTrackingEnabled(direction);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        SwipeBackUtils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
