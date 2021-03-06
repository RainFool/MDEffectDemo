package com.rainfool.skinengine;

import android.content.res.Resources;

public interface ILoadTaskListener {
    void onStart();

    void onSuccess(Resources resources, String packageName);

    void onFailed();
}
