package com.rainfool.md.softinput

import android.R
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.rainfool.md.BaseApp
import kotlin.math.abs

/**
 * 键盘相关帮助类
 * @author krystian
 */
object KeyboardHelper {

    private const val TAG_ON_GLOBAL_LAYOUT_LISTENER = -8

    private const val SP_KEY_KEYBOARD_HEIGHT = "KeyboardHelper_keyboard_height"

    private var sDecorViewDelta = 0

    fun showSoftInput(view: View) {
        showSoftInput(view, 0)
    }

    fun hideSoftInput(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun registerSoftInputChangedListener(inputBoxView: View, window: Window, listener: (Int) -> Unit) {
        val flags = window.attributes.flags
        if (flags and WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS != 0) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        val contentView = window.findViewById<FrameLayout>(R.id.content)
        var decorViewInvisibleHeightPre = getDecorViewInvisibleHeight(window)
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val height: Int = getDecorViewInvisibleHeight(window)
            if (decorViewInvisibleHeightPre != height) {
                listener.invoke(height)
                decorViewInvisibleHeightPre = height
            }
        }
        contentView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        inputBoxView.setTag(TAG_ON_GLOBAL_LAYOUT_LISTENER, onGlobalLayoutListener)
    }

    fun unregisterSoftInputChangedListener(inputBoxView: View, window: Window) {
        val contentView = window.findViewById<View>(R.id.content) ?: return
        val tag = inputBoxView.getTag(TAG_ON_GLOBAL_LAYOUT_LISTENER)
        if (tag is ViewTreeObserver.OnGlobalLayoutListener) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                contentView.viewTreeObserver.removeOnGlobalLayoutListener(tag)
            }
        }
    }


    private fun showSoftInput(view: View, flags: Int) {
        val imm = view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        imm.showSoftInput(view, flags, object : ResultReceiver(Handler()) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN
                    || resultCode == InputMethodManager.RESULT_HIDDEN
                ) {
                    toggleSoftInput()
                }
            }
        })
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun toggleSoftInput() {
        val imm = BaseApp.gContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, 0)
    }

    private fun getDecorViewInvisibleHeight(window: Window): Int {
        val decorView = window.decorView
        val outRect = Rect()
        decorView.getWindowVisibleDisplayFrame(outRect)
        val delta = abs(decorView.bottom - outRect.bottom)
        if (delta <= 200f) {
            sDecorViewDelta = delta
            return 0
        }
        return delta - sDecorViewDelta
    }

}