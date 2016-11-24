package com.bowyoung.enhancelibrary.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bowyoung.enhancelibrary.base.activitiy.BaseEnhanceActivity;

/**
 * Created by S0S on 16/4/29.
 */
public abstract class BaseEnhanceFragment extends Fragment {

    protected Context mContext;
    private View mView;

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract View getContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mView = getContentView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView == null ? super.onCreateView(inflater, container, savedInstanceState) : mView;
    }

    protected View findViewById(int id) {
        return mView.findViewById(id);
    }

    @Nullable
    @Override
    public View getView() {
        return mView;
    }

    protected void startActivityAndFinish(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        if (mContext instanceof BaseEnhanceActivity) {
            ((BaseEnhanceActivity) mContext).finish();
        }
    }

    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

}
