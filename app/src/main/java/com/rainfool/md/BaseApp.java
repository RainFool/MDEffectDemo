package com.rainfool.md;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.rainfool.skinengine.SkinEngine;

/**
 * @author rainfool
 * @date 2018/5/30
 */

public class BaseApp extends Application {

    public static Context gContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        SkinEngine.getInstance().init(this);
        gContext = this;
    }
}
