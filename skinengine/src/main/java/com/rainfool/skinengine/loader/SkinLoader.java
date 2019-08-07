package com.rainfool.skinengine.loader;

import android.content.Context;

import com.rainfool.skinengine.ILoadTaskListener;
import com.rainfool.skinengine.ISkinLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SkinLoader implements ISkinLoader {

    private Context mContext;

    public SkinLoader(Context context) {
        this.mContext = context;
    }

    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    @Override
    public void load(String skinPath, ILoadTaskListener listener) {
        mExecutor.submit(new LoadTask(mContext, skinPath, listener));
    }
}
