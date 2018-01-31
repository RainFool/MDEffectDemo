package com.example.app.popup;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.app.R;

public class PopupTestActivity extends Activity {

    private static final String TAG = "PopupTestActivity";

    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_test);
        mTextView = findViewById(R.id.anchor);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    private void showPopup(View v) {
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        int flags = getWindow().getAttributes().flags;

        boolean isDrawSystemBar = (flags & WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) == 0;
        Log.d(TAG, "showPopup: isDrawSystemBar: " + isDrawSystemBar);


        Log.d(TAG, "showPopup,v top:" + getRectInWindow(v).top + ",screen height:" + screenHeight);

        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.layout_popup_text, null);
        PopupWindow popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        int bottom = screenHeight - getRectInWindow(v).top;
        Log.d(TAG, "final bottom:" + bottom);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 1460);
    }

    private static Rect getRectInWindow(View view) {
        final int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
    }
}