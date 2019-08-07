package com.rainfool.skinengine;

import android.app.Application;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.rainfool.skinengine.loader.SkinLoader;

import java.io.File;

public class SkinEngine {

    private static final String TAG = "SkinEngine";

    private static final String SKIN_NAME = "BlackFantacy.skin";

    private static String SKIN_DIR;

    private static class InstanceHolder {
        private static final SkinEngine INSTANCE = new SkinEngine();
    }

    public static SkinEngine getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private SkinEngine() {
    }

    public void init(Application app) {
        SKIN_DIR = app.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + SKIN_NAME;

        ISkinLoader skinLoader = new SkinLoader(app);
        skinLoader.load(SKIN_DIR, new ILoadTaskListener() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onSuccess(Resources resources) {
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onFailed() {
                Log.d(TAG, "onFailed: ");
            }
        });
    }
}
