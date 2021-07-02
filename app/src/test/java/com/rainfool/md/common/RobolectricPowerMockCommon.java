package com.rainfool.md.common;

import android.util.Log;
import androidx.emoji.text.EmojiCompat;

import com.rainfool.md.BaseApp;

import com.rainfool.md.UTBaseApp;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.QQRobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by vashzhong on 13,May,2021
 */
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "androidx.*"})
@RunWith(QQRobolectricTestRunner.class)
@PrepareForTest({Log.class, EmojiCompat.class})
@Config(application = UTBaseApp.class, manifest = Config.NONE)
public abstract class RobolectricPowerMockCommon {
    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();

    @Before
    public void setUp() throws Exception {
    }

    public boolean useShadow() {
        return false;
    }
}
