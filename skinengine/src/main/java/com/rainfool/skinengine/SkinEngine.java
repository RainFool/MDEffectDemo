package com.rainfool.skinengine;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SkinEngine {

    private static class InstanceHolder {
        private static final SkinEngine INSTANCE = new SkinEngine();
    }

    public static SkinEngine getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private SkinEngine() {
    }

    public void init(Application app) {
    }
}
