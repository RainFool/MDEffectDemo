package com.rainfool.skinengine.loader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.rainfool.skinengine.ILoadTaskListener;

import java.io.File;
import java.lang.reflect.Method;

public class LoadTask implements Runnable {

    private final Context mContext;
    private final String mSkinFilePath;
    private final ILoadTaskListener mListener;

    public LoadTask(Context mContext, String mSkinFilePath, ILoadTaskListener mListener) {
        this.mContext = mContext;
        this.mSkinFilePath = mSkinFilePath;
        this.mListener = mListener;
    }

    @Override
    public void run() {
        mListener.onStart();
        File file = new File(mSkinFilePath);
        if (!file.exists()) {
            mListener.onFailed();
            return;
        }

        PackageManager mPm = mContext.getPackageManager();
        try {
            PackageInfo mInfo = mPm.getPackageArchiveInfo(mSkinFilePath, PackageManager.GET_ACTIVITIES);


            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, mSkinFilePath);


            Resources superRes = mContext.getResources();
            Resources skinResource = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());

            mListener.onSuccess(skinResource);
        } catch (Exception e) {
            e.printStackTrace();
            mListener.onFailed();
        }
    }
}
