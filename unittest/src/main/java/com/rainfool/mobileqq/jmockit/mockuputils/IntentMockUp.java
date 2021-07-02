package com.rainfool.mobileqq.jmockit.mockuputils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mockit.Mock;
import mockit.MockUp;

public class IntentMockUp extends MockUp<Intent> {

    private BundleMockUp mBundleMockUp;
    private Map<String, Object> mDataMap = new HashMap<>();
    private Bundle bundle;

    @Mock
    public void $init() {
    }

    // mock静态初始代码块
    @Mock
    public void $clinit() {

    }

    // 单测避免使用连着的putExtra
    @Mock
    public Intent putExtra(String name, String value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Serializable getSerializableExtra(String name) {
        return (Serializable) mDataMap.get(name);
    }

    @Mock
    public boolean hasExtra(String name) {
        return mDataMap.containsKey(name);
    }

    @Mock
    public Intent putExtra(String name, Bundle value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, byte[] value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent setFlags(int flags) {
        return null;
    }

    @Mock
    public Bundle getBundleExtra(String name) {
        return (Bundle) mDataMap.get(name);
    }

    @Mock
    public int getIntExtra(String key, int defaultValue) {
        return mDataMap.containsKey(key) ? (int) mDataMap.get(key) : defaultValue;
    }

    @Mock
    public String getStringExtra(String key) {
        return mDataMap.containsKey(key) ? (String) mDataMap.get(key) : null;
    }

    @Mock
    public short getShortExtra(String name, short defaultValue) {
        return mDataMap.containsKey(name) ? (short) mDataMap.get(name) : defaultValue;
    }

    @Mock
    public boolean getBooleanExtra(String name, boolean defaultValue) {
        return mDataMap.containsKey(name) ? (boolean) mDataMap.get(name) : defaultValue;
    }

    @Mock
    public <T extends Parcelable> T getParcelableExtra(String name) {
        return (T) mDataMap.get(name);
    }

    @Mock
    public byte getByteExtra(String name, byte defaultValue) {
        return mDataMap.containsKey(name) ? (byte) mDataMap.get(name) : defaultValue;
    }

    @Mock
    public char getCharExtra(String name, char defaultValue) {
        return mDataMap.containsKey(name) ? (char) mDataMap.get(name) : defaultValue;
    }

    @Mock
    public long getLongExtra(String name, long defaultValue) {
        return mDataMap.containsKey(name) ? (long) mDataMap.get(name) : defaultValue;
    }

    @Mock
    public Intent putExtra(String name, Serializable value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, boolean value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, byte value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, char value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, short value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, int value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, long value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, float value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, double value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, CharSequence value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, Parcelable value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putExtra(String name, Parcelable[] value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putParcelableArrayListExtra(String name, ArrayList<? extends Parcelable> value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putIntegerArrayListExtra(String name, ArrayList<Integer> value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putStringArrayListExtra(String name, ArrayList<String> value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent putCharSequenceArrayListExtra(String name, ArrayList<CharSequence> value) {
        mDataMap.put(name, value);
        return null;
    }

    @Mock
    public Intent setClass(Context packageContext, Class<?> cls) {
        return null;
    }

    @Mock
    public Bundle getExtras() {
        mBundleMockUp.setDataMap(mDataMap);
        return new Bundle();
    }

    @Mock
    public Intent putExtras(Intent src) {
        return null;
    }

}