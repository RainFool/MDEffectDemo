package com.rainfool.md.skin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.rainfool.md.R;
import com.rainfool.skinengine.SkinInflateFactory;

import java.io.File;

public class SkinDemoActivity extends AppCompatActivity {

    private static final String TAG = "SkinDemoActivity";

    private static final String SKIN_NAME = "BlackFantacy.skin";

    private static String SKIN_DIR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new SkinInflateFactory());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_demo);

        SKIN_DIR = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + SKIN_NAME;

    }
}
