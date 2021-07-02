package com.rainfool.md.fakenotification

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import java.lang.ref.WeakReference


/**
 * 仿通知栏效果的view
 * @author krystian
 */
class FakeNotificationContainer : FrameLayout {

    companion object {
        private const val TAG = "TopSheetContainer"
        private const val ANIM_DURATION = 300L

    }

    private lateinit var mDetector: GestureDetector

    private lateinit var contentViewContainer: FrameLayout

    private var onDismissListener: IFakeNotificationDismissListener? = null

    private var needDismiss = false

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    fun addContentView(content: View) {
        reset()
        contentViewContainer.addView(content)
        appearAnim()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val detectedUp = event.action == MotionEvent.ACTION_UP
        if (!mDetector.onTouchEvent(event) && detectedUp) {
            if (needDismiss) {
                dismissAnim()
            }
        }
        return true
    }

    internal fun setOnDismissListener(listener: IFakeNotificationDismissListener) {
        this.onDismissListener = listener
    }

    private fun initView(context: Context) {
        contentViewContainer = FrameLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(contentViewContainer, params)
        mDetector = GestureDetector(context, TopSheetGestureListener(contentViewContainer))
    }

    private fun reset() {
        contentViewContainer.removeAllViews()
        contentViewContainer.translationY = 0F
    }


    private fun settleAnim() {
        ObjectAnimator.ofFloat(contentViewContainer, "translationY", contentViewContainer.translationY, 0F)
            .setDuration(ANIM_DURATION)
            .start()
    }

    private fun dismissAnim() {
        val anim = ObjectAnimator.ofFloat(
            contentViewContainer,
            "translationY",
            contentViewContainer.translationY,
            -contentViewContainer.measuredHeight.toFloat() * 2
        )
            .setDuration(ANIM_DURATION)
        anim.interpolator = LinearInterpolator()
        anim.start()
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                this@FakeNotificationContainer.visibility = View.GONE
                onDismissListener?.onFakeNotificationDismiss()
            }
        })
        needDismiss = false
    }

    private fun appearAnim() {
        val anim = ObjectAnimator.ofFloat(
            contentViewContainer,
            "translationY",
            -contentViewContainer.measuredHeight.toFloat() * 2,
            0F
        )
            .setDuration(ANIM_DURATION)
        anim.interpolator = DecelerateInterpolator()
        anim.start()
        this.visibility = View.VISIBLE
    }

    inner class TopSheetGestureListener(animView: View) : GestureDetector.OnGestureListener {

        private val animViewRef = WeakReference<View>(animView)

        override fun onDown(e: MotionEvent) = false

        override fun onShowPress(e: MotionEvent) {
        }

        override fun onSingleTapUp(e: MotionEvent) = true

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            val curContentView = animViewRef.get() ?: return false
            val totalTransY = e2.y - e1.y
            if (totalTransY < -(curContentView.measuredHeight / 2)) {
                needDismiss = true
            }
            return false
        }

        override fun onLongPress(e: MotionEvent) {

        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            Log.d(TAG, "onFling,velocityY:$velocityY")
            if (velocityY < -1000) {
                needDismiss = true
            }
            return false
        }

    }

    interface IFakeNotificationDismissListener {
        fun onFakeNotificationDismiss()
    }

}