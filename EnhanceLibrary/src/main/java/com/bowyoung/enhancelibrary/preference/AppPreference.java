package com.bowyoung.enhancelibrary.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private SharedPreferences mSharedPref;

    public static AppPreference get(Context context, String name) {
        return new AppPreference(context, name);
    }

    public AppPreference(Context context, String name) {
        mSharedPref = context.getSharedPreferences(name, Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);
    }

    public void save(String key, String value) {
        if (mSharedPref != null) {
            mSharedPref.edit().putString(key, value).apply();
        }
    }

    public void save(String key, long value) {
        if (mSharedPref != null) {
            mSharedPref.edit().putLong(key, value).apply();
        }
    }

    public void save(String key, int value) {
        if (mSharedPref != null) {
            mSharedPref.edit().putInt(key, value).apply();
        }
    }

    public void save(String key, boolean value) {
        if (mSharedPref != null) {
            mSharedPref.edit().putBoolean(key, value).apply();
        }
    }

    public String get(String key) {
        return get(key, "");
    }

    public String get(String key, String defaultValue) {
        if (mSharedPref == null) {
            return defaultValue;
        }
        return mSharedPref.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        if (mSharedPref == null) {
            return defaultValue;
        }
        return mSharedPref.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        if (mSharedPref == null) {
            return defaultValue;
        }
        return mSharedPref.getLong(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (mSharedPref == null) {
            return defaultValue;
        }
        return mSharedPref.getBoolean(key, defaultValue);
    }

    /**
     * 保存
     *
     * @param context
     * @param name
     * @param key
     * @param value
     */
    public static void save(Context context, String name, String key, String value) {
        AppPreference preference = get(context, name);
        if (preference != null) {
            preference.save(key, value);
        }
    }

    /**
     * 读取
     *
     * @param context
     * @param name
     * @param key
     */
    public static String get(Context context, String name, String key) {
        return get(context, name, key, "");
    }

    /**
     * 读取
     *
     * @param context
     * @param name
     * @param key
     */
    public static String get(Context context, String name, String key, String defaultValue) {
        AppPreference preference = get(context, name);
        if (preference == null) {
            return defaultValue;
        }
        return preference.get(key, defaultValue);
    }

    public void clear() {
        if (mSharedPref != null) {
            mSharedPref.edit().clear().apply();
        }
    }
}
