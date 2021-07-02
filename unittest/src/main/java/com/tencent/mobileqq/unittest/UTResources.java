package com.tencent.mobileqq.unittest;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author: jensenweng
 * @data: 2021/5/21
 * @Desp: 单测专用的android Resources类，配合UTBaseApplicationImpl使用
 */
public class UTResources extends Resources {
    DisplayMetrics displayMetrics = null;
    Configuration configuration = null;

    public UTResources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
        this.displayMetrics = metrics;
        this.configuration = config;
    }

    @Override
    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
