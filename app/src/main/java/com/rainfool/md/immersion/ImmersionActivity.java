package com.rainfool.md.immersion;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.rainfool.md.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 沉浸式Demo
 *
 * @author rainfool
 * @date 2017/12/8
 */

public class ImmersionActivity extends Activity {

    Button mBtnTransparent, mBtnWhite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setImmersion();
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_immersion);

        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        getWindow().setEnterTransition(transition);
        mBtnTransparent = (Button) findViewById(R.id.btn_transparent);
        mBtnWhite = (Button) findViewById(R.id.btn_white);
    }


    private void setImmersion() {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    updateStatusBarColorForM(false);
                }
                StatusBarUtil.setImmersionMode(ImmersionActivity.this, false);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updateStatusBarColorForM(boolean isDark) {
        final Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(ContextCompat.getColor(this, isDark ? android.R.color.black : android.R.color.white));

        int ui = window.getDecorView().getSystemUiVisibility();
        if (isDark) {
            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }

        window.getDecorView().setSystemUiVisibility(ui);
    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
