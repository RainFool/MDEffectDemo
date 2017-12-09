package com.example.app.immersion;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.app.R;

/**
 * 沉浸式Demo
 *
 * @author rainfool
 * @date 2017/12/8
 */

public class ImmersionActivity extends AppCompatActivity {

    Button mBtnTransparent,mBtnWhite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion);

        mBtnTransparent = findViewById(R.id.btn_transparent);
        mBtnWhite = findViewById(R.id.btn_white);

        mBtnTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtil.setImmersionMode(ImmersionActivity.this,false);
                changeToTransparent();
            }
        });

        mBtnWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtil.setImmersionMode(ImmersionActivity.this,true);
                changeToWhite();
            }
        });
    }

    private void changeToTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final Window window = getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getColor(R.color.color_tranparent_dark));

        }
    }

    private void changeToWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final Window window = getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getColor(android.R.color.white));

        }
    }
}
