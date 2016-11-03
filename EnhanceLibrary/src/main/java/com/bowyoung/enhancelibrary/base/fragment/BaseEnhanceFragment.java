package com.bowyoung.enhancelibrary.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bowyoung.enhancelibrary.widget.HintToast;

/**
 * Created by S0S on 16/4/29.
 */
public abstract class BaseEnhanceFragment extends Fragment {

    protected Context mContext;

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewID();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewID() != 0) {
            mContext = getActivity();
            View view = inflater.inflate(getContentViewID(), null);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    public void startActivityAndFinish(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    public void startActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    protected void showToast(String msg) {
        HintToast.showToast(mContext, msg);
    }

    protected void showToast(int resId) {
        HintToast.showToast(mContext, resId);
    }

    protected void showToast(String msg, int duration) {
        HintToast.showToast(mContext, msg, duration);
    }

    protected void showToast(int resId, int duration) {
        HintToast.showToast(mContext, resId, duration);
    }
}
