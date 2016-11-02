package com.bowyoung.enhancelibrary.widget;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class HintToast {

	public static final int LONG = 1500;
	public static final int SHORT = 1000;

	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	public static void showToast(Context mContext, String text, int duration) {
		mHandler.removeCallbacks(r);
		if (mToast != null)
			mToast.setText(text);
		else
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		mHandler.postDelayed(r, duration);
		mToast.show();
	}

	public static void showToast(Context mContext, int resId, int duration) {
		showToast(mContext, mContext.getResources().getString(resId), duration);
	}

	public static void showToast(Context mContext, int resId) {
		showToast(mContext, mContext.getResources().getString(resId), SHORT);
	}

	public static void showToast(Context mContext, String text) {
		showToast(mContext, text, SHORT);
	}

}
