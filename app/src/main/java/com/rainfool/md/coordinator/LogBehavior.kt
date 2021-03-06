package com.rainfool.md.coordinator

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View

class LogBehavior : androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior<View>() {

    private val TAG = "LogBehavior"

    init {
        Log.d(TAG, "init")
    }

    override fun onAttachedToLayoutParams(params: androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) {
        super.onAttachedToLayoutParams(params)
        Log.d(TAG, "onAttachedToLayoutParams")
    }

    override fun onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams()
        Log.d(TAG, "onDetachedFromLayoutParams")

    }

    override fun onInterceptTouchEvent(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent")

        return true
    }

    override fun onTouchEvent(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        Log.d(TAG, "onTouchEvent ${ev?.action}")

        return super.onTouchEvent(parent, child, ev)
    }

    override fun layoutDependsOn(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, dependency: View): Boolean {
        Log.d(TAG, "layoutDependsOn:child:${child?.id},dependency:${dependency?.id}")

        return true
    }

    override fun onDependentViewChanged(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, dependency: View): Boolean {
        Log.d(TAG, "onDependentViewChanged")

        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onDependentViewRemoved(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, dependency: View) {
        Log.d(TAG, "onDependentViewRemoved")

        super.onDependentViewRemoved(parent, child, dependency)
    }

    override fun onMeasureChild(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {
        Log.d(TAG, "onMeasureChild,child:${child?.id}")
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }

    override fun onLayoutChild(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
        Log.d(TAG, "onLayoutChild,child:${child?.id}")

        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        Log.d(TAG, "onStartNestedScroll,child:${child.id}")

        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScrollAccepted(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int) {
        Log.d(TAG, "onNestedScrollAccepted,child:${child.id}")

        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onStopNestedScroll(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, target: View, type: Int) {
        Log.d(TAG, "onStopNestedScroll,child:${child.id}")
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }

    override fun onNestedScroll(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.d(TAG, "onNestedScroll,child:${child.id}")

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onNestedPreScroll(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.d(TAG, "onNestedPreScroll,child:${child.id}")

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onNestedFling(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        Log.d(TAG, "onNestedFling,child:${child.id}")

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onNestedPreFling(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.d(TAG, "onNestedPreFling,child:${child.id}")
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onApplyWindowInsets(coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, insets: WindowInsetsCompat): WindowInsetsCompat {
        Log.d(TAG, "onApplyWindowInsets,child:${child?.id}")

        return super.onApplyWindowInsets(coordinatorLayout, child, insets)
    }
}