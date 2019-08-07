package com.rainfool.skinengine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SkinInflateFactory implements LayoutInflater.Factory2 {

    private static final String TAG = "SkinInflateFactory";

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d(TAG, "onCreateView: name %s" + name);
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            Log.d(TAG, "onCreateView: att name:" + attrs.getAttributeName(i));
        }
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.d(TAG, "onCreateView: name %s" + name);
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            Log.d(TAG, "onCreateView: att name:" + attrs.getAttributeName(i));
        }
        return null;
    }
}
