package com.tencent.mobileqq.partyhouse.im.input

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.rainfool.md.softinput.*
import com.rainfool.md.softinput.InputViewStateConst.STATE_FUNC
import com.rainfool.md.softinput.InputViewStateConst.STATE_KEYBOARD
import com.rainfool.md.softinput.InputViewStateConst.STATE_NORMAL
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

/**
 *
 * @author krystian
 */
abstract class BaseInputView : LinearLayout{

    companion object {
        private const val TAG = "BaseInputView"

    }

    /**
     * 当前状态，设置后会自动变化
     */
    var currentState: Int by Delegates.observable(STATE_NORMAL) { _, old, new ->
        renderState(old, new)
    }

    private lateinit var inputLayout: FrameLayout

    private lateinit var funcLayout: FrameLayout

    private val stateChangedListenerList = CopyOnWriteArrayList<StateChangedListener>()

    private var keyboardHeight = 400

    private val layoutListener: (Int) -> Unit = {
        if (it > 0) {
            keyboardHeight = it
            currentState = STATE_KEYBOARD
        } else if (currentState == STATE_KEYBOARD) {
            currentState = STATE_NORMAL
        }
    }

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val window = getWindowOrNull() ?: return
        KeyboardHelper.registerSoftInputChangedListener(this, window, layoutListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        val window = getWindowOrNull() ?: return
        KeyboardHelper.unregisterSoftInputChangedListener(this, window)
    }

    private fun initView() {
        orientation = VERTICAL
        inputLayout = FrameLayout(context)
        addView(inputLayout, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        inflate(context, getLayoutId(), inputLayout)

        funcLayout = FrameLayout(context).apply {
            setBackgroundColor(Color.WHITE)
        }
        addView(funcLayout, LayoutParams(LayoutParams.MATCH_PARENT, 0))
        onLayoutInflated()
    }

    // 设置键盘的状态
    private fun renderState(oldState: Int, newState: Int) {
        val inputEditText = getInputEditText() ?: return
//
//        if (currentState != STATE_KEYBOARD) {
//            KeyboardHelper.hideSoftInput(this)
//        }
//
//        if (currentState == STATE_NORMAL) {
//            inputEditText.clearFocus()
//            funcLayout.post {
//                funcLayout.removeAllViews()
//            }
//        }
//        funcLayout.post {
//            funcLayout.setHeightWithAnim(if (currentState == STATE_FUNC) keyboardHeight else 0)
//        }
//        if (newState == STATE_FUNC || newState == STATE_NORMAL) {
//            funcLayout.post {
//                WebTIMInputUtils.hideSoftKeyBroad(inputEditText.context, inputEditText)
//            }
//        }
        getInputEditText()?.let {
            if (currentState != STATE_KEYBOARD) {
                funcLayout.post {
                    KeyboardHelper.hideSoftInput(this)
                }
            }
        }

        when (currentState) {
            STATE_NORMAL, STATE_FUNC -> {
                val animDuration = 500L
                funcLayout.setHeightWithAnim(if (currentState == STATE_NORMAL) 0 else keyboardHeight, animDuration)
                if (currentState == STATE_NORMAL) {
                    getInputEditText()?.clearFocus()
                    funcLayout?.postDelayed({
                        funcLayout?.removeAllViews()
                    }, animDuration)
                }
            }
            STATE_KEYBOARD -> {
                funcLayout?.setHeight(if (currentState == STATE_NORMAL) 0 else keyboardHeight)
                if (currentState == STATE_NORMAL) {
                    getInputEditText()?.clearFocus()
                    funcLayout?.post {
                        funcLayout?.removeAllViews()
                    }
                }
            }
        }
        stateChangedListenerList.forEach { it.onStateChanged(currentState) }
    }

    protected open fun onDelKeyClick() {
        getInputEditText()?.let {
            val selectionStart: Int = it.selectionStart //获取光标所在位置
            val inputText = if (it.editableText != null) it.editableText.toString() else ""
            var beforeText = inputText.substring(0, selectionStart)
            val count = beforeText.codePointCount(0, beforeText.length)

            val deleteCount: Int = deleteContent(beforeText, Int.MAX_VALUE)
            beforeText = beforeText.substring(
                beforeText.offsetByCodePoints(0, 0),
                beforeText.offsetByCodePoints(0, max(count - deleteCount, 0))
            )
            val afterText = inputText.substring(selectionStart, inputText.length)
            val finalText = beforeText + afterText
            it.setText(finalText)
            it.setSelection(min(beforeText.length, finalText.length))
        }
    }


    protected open fun deleteContent(content: String, maxLength: Int): Int {
        return 1
    }

    // 在发送按钮点击事件最开始处回调
    protected open fun doBeforeSend(view: View) {}

    abstract fun getInputEditText(): EditText?

    abstract fun getSendBtn(): View?

    abstract fun getEmojiBtn(): View?

    abstract fun getLayoutId(): Int

    abstract fun onLayoutInflated()


}