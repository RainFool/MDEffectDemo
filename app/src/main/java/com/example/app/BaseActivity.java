package com.example.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author rainfool
 * @date 2018/10/19
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getTag(), "onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getTag(), "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getTag(), "onPause: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getTag(), "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getTag(), "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getTag(), "onDestroy: ");
    }

    protected String getTag() {
        return getClass().getSimpleName();
    }
}
