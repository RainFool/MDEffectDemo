package com.rainfool.md.softinput

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.view.Window
import androidx.annotation.Keep
import androidx.core.view.animation.PathInterpolatorCompat

/**
 * TIM相关view帮助类
 * @author krystian
 */

fun View.getWindowOrNull(): Window? {
    return (context as? Activity)?.window
}

fun View.setHeight(pendingHeight: Int) {
    layoutParams = layoutParams.apply {
        height = pendingHeight
    }
}


fun View.setHeightWithAnim(targetHeight: Int, duration: Long = 500) {
    if (layoutParams == null) {
        return
    }
    val viewWrapper = ViewWrapper(this)
    val currentHeight = viewWrapper.height
    if (currentHeight == targetHeight) {
        return
    }
    val showInterpolator = PathInterpolatorCompat.create(.1f, .8f, .2f, 1f)

    val dismissInterpolator = PathInterpolatorCompat.create(.1f, .8f, .2f, 1f)
    val anim = ObjectAnimator.ofInt(viewWrapper, "height", currentHeight, targetHeight)
    anim.interpolator = if (currentHeight > targetHeight) {
        dismissInterpolator
    } else {
        showInterpolator
    }
    anim.duration = duration
    anim.addUpdateListener {
    }
    postDelayed({
        //兜底delayTask
        viewWrapper.height = targetHeight
    }, duration + 20)
    anim.start()
}

@Keep
class ViewWrapper(target: View) {
    private val rView: View = target
    var width: Int
        get() = rView.layoutParams.width
        set(width) {
            rView.layoutParams.width = width
            rView.requestLayout()
        }
    var height: Int
        get() = rView.layoutParams.height
        set(height) {
            rView.layoutParams.height = height
            rView.requestLayout()
        }

    fun getView(): View {
        return rView
    }
}