package com.rainfool.md.nestscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

/**
 * @author rainfool
 * @date 2018/1/31
 */

public class LockableScrollView extends ScrollView {

    private static final String TAG = "CustomScrollView";


    // true if we can scroll the ScrollView
    // false if we cannot scroll
    private boolean scrollable = true;


    public LockableScrollView(Context context) {
        super(context);
    }

    public LockableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LockableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setScrollingEnabled(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:

                if (scrollable) {
                    return super.onTouchEvent(ev);
                } else {
                    return false;
                }
            default:
                return super.onTouchEvent(ev);
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.d(TAG, "onOverScrolled");
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, "onNestedScroll");
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (scrollable) {
            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        } else {
            // do nothing
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        if (!scrollable) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }
}
