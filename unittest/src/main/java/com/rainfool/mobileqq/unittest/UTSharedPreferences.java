package com.rainfool.mobileqq.unittest;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;

/**
 * @author: jensenweng
 * @data: 2021/5/21
 * @Desp: 单测专用的空SharedPreferences，配合UTBaseApplicationImpl使用
 *        该类不会执行真实SP操作，若需要依赖真实的SP结果，需要指定Robolectric和QFixApplicationImpl
 */
public class UTSharedPreferences implements SharedPreferences {
    @Override
    public Map<String, ?> getAll() {
        return null;
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return defValue;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return defValues;
    }

    @Override
    public int getInt(String key, int defValue) {
        return defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        return defValue;
    }

    @Override
    public float getFloat(String key, float defValue) {
        return defValue;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return defValue;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public Editor edit() {
        return new Editor() {
            @Override
            public Editor putString(String key, @Nullable String value) {
                return this;
            }

            @Override
            public Editor putStringSet(String key, @Nullable Set<String> values) {
                return this;
            }

            @Override
            public Editor putInt(String key, int value) {
                return this;
            }

            @Override
            public Editor putLong(String key, long value) {
                return this;
            }

            @Override
            public Editor putFloat(String key, float value) {
                return this;
            }

            @Override
            public Editor putBoolean(String key, boolean value) {
                return this;
            }

            @Override
            public Editor remove(String key) {
                return this;
            }

            @Override
            public Editor clear() {
                return this;
            }

            @Override
            public boolean commit() {
                return false;
            }

            @Override
            public void apply() {

            }
        };
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }
}
