package com.rainfool.md.skin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.os.Bundle;
import android.util.Log;

import com.rainfool.md.R;
import com.rainfool.skinengine.SkinInflateFactory;

public class SkinDemoActivity extends AppCompatActivity {

    private static final String TAG = "SkinDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new SkinInflateFactory());
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:2 ");

        setContentView(R.layout.activity_skin_demo);
        Log.d(TAG, "onCreate: 3");

    }
}
