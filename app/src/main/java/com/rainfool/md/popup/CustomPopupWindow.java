package com.rainfool.md.popup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

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
}
