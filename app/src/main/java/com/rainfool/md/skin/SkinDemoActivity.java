package com.rainfool.md.skin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;

import com.rainfool.md.R;
import com.rainfool.skinengine.ILoadTaskListener;
import com.rainfool.skinengine.SkinEngine;
import com.rainfool.skinengine.SkinInflateFactory;

import java.io.File;

public class SkinDemoActivity extends AppCompatActivity {

    private static final String TAG = "SkinDemoActivity";

    private static final String SKIN_NAME = "BlackFantacy.skin";

    private static String SKIN_DIR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SkinInflateFactory factory = new SkinInflateFactory();
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), factory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_demo);

        SKIN_DIR = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + SKIN_NAME;
        SkinEngine.getInstance().startLoad(new ILoadTaskListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Resources resources, String packageName) {
                factory.applySkin(SkinDemoActivity.this,resources,packageName);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
