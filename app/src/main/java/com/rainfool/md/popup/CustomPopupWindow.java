package com.rainfool.md.popup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class CustomPopupWindow extends PopupWindow {

    public CustomPopupWindow(Context context) {
        super(context);
    }

    public CustomPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomPopupWindow() {
    }

    public CustomPopupWindow(View contentView) {
        super(contentView);
    }

    public CustomPopupWindow(int width, int height) {
        super(width, height);
    }

    public CustomPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public CustomPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public void ensureDismiss(Lifecycle lifecycle) {
        lifecycle.addObserver(new MyObserver(this, lifecycle));
    }

    public static class MyObserver implements LifecycleObserver {

        CustomPopupWindow customPopupWindow;
        Lifecycle lifecycle;

        public MyObserver(CustomPopupWindow customPopupWindow, Lifecycle lifecycle) {
            this.customPopupWindow = customPopupWindow;
            this.lifecycle = lifecycle;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void connectListener() {
            Log.d("rainfool", "ON_START");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void disconnectListener() {
            Log.d("rainfool", "ON_STOP");
            customPopupWindow.dismiss();
            lifecycle.removeObserver(this);
            customPopupWindow = null;

        }
    }

}
