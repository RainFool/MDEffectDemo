package com.example.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

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
        gContext = this;
    }
}
