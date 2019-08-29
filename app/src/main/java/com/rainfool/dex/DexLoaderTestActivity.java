package com.rainfool.dex;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.dexloader.DexLoader;
import com.rainfool.md.R;

public class DexLoaderTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex_loader_test);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] strings = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(strings, 0);
        }
    }

    public void onLoadDexClicked(View view) {
        DexLoader.loadFixedDex(this, Environment.getExternalStorageDirectory());
    }

    public void onCalculateClicked(View view) {
        int result = 2 / 0;
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }
}
