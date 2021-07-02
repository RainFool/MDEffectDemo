package com.tencent.mobileqq.jmockit.mockuputils;

import android.os.BaseBundle;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import mockit.Mock;
import mockit.MockUp;

/**
 * android.os.Bundle已经通过qmock.jar注入，所有jmockit用例都可以不用去new BundleMockUp来避免异常
 */
public class BundleMockUp extends MockUp<Bundle> {
    private Map<String, Object> mDataMap = new HashMap<>();
    BaseBundleMockUp mBundleMockUp = new BaseBundleMockUp();

    public BundleMockUp(){
    }

    public BundleMockUp(Map<String, Object> map) {
        mDataMap = map;
    }

    public void setDataMap(Map<String, Object> map) {
        mDataMap = map;
    }

    @Mock
    public void $init() {
    }

    // mock静态初始代码块
    @Mock
    public void $clinit() {

    }

    @Mock
    public int getInt(String key, int defaultValue) {
        return mDataMap.containsKey(key) ? (int) mDataMap.get(key) : defaultValue;
    }

    @Mock
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Mock
    public String getString(String key, String defaultValue) {
        return mDataMap.containsKey(key) ? (String) mDataMap.get(key) : defaultValue;
    }

    @Mock
    public String getString(String key) {
        return getString(key, null);
    }

    @Mock
    public void putString(String key, String value) {
        mDataMap.put(key, value);
    }

    @Mock
    public void putInt(String key, int value) {
        mDataMap.put(key, value);
    }

    @Mock
    public void putSerializable(String key, Serializable value) {
        mDataMap.put(key, value);
    }

    @Mock
    public <T extends Parcelable> T getParcelable(String key) {
        return (T) mDataMap.get(key);
    }

    @Mock
    public Serializable getSerializable(String key) {
        return (Serializable) mDataMap.get(key);
    }

    @Mock
    public void putParcelable(String key, Parcelable value) {
        mDataMap.put(key, value);
    }

    class BaseBundleMockUp extends MockUp<BaseBundle> {
        @Mock
        public void $init() {
        }

        // mock静态初始代码块
        @Mock
        public void $clinit() {

        }

        @Mock
        public int getInt(String key, int defaultValue) {
            return mDataMap.containsKey(key) ? (int) mDataMap.get(key) : defaultValue;
        }

        @Mock
        public int getInt(String key) {
            return getInt(key, 0);
        }

        @Mock
        public String getString(String key, String defaultValue) {
            return mDataMap.containsKey(key) ? (String) mDataMap.get(key) : defaultValue;
        }

        @Mock
        public String getString(String key) {
            return getString(key, null);
        }

        @Mock
        public void putString(String key, String value) {
            mDataMap.put(key, value);
        }

        @Mock
        public void putBoolean(String key, boolean value) {
            mDataMap.put(key, value);
        }

        @Mock
        public boolean getBoolean(String key) {
            return mDataMap.containsKey(key) ? (Boolean) mDataMap.get(key) : false;
        }

        @Mock
        public boolean getBoolean(String key, boolean defaultValue) {
            return mDataMap.containsKey(key) ? (Boolean) mDataMap.get(key) : defaultValue;
        }

        @Mock
        public void putInt(String key, int value) {
            mDataMap.put(key, value);
        }

        @Mock
        public void putSerializable(String key, Serializable value) {
            mDataMap.put(key, value);
        }

        @Mock
        public Serializable getSerializable(String key) {
            return (Serializable) mDataMap.get(key);
        }
    }
}

