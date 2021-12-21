package com.rainfool.md.softinput

import androidx.annotation.IntDef

/**
 * 输入法相关接口
 */
interface IInputView {

    fun showKeyBoard()

    fun hideKeyboard()

    fun addStateChangedListener(listener: StateChangedListener)

    fun removeStateChangedListener(listener: StateChangedListener)
}

/**
 * 输入框状态变更通知
 */
interface StateChangedListener {

    fun onText(text: String, timiBaseInputView: IInputView)
    fun onStateChanged(state: Int)
}

@IntDef(InputViewStateConst.STATE_NORMAL, InputViewStateConst.STATE_KEYBOARD, InputViewStateConst.STATE_FUNC)
@Retention(AnnotationRetention.SOURCE)
annotation class InputViewState

/**
 * 输入框状态
 */
object InputViewStateConst {
    const val STATE_NORMAL = 0x01
    const val STATE_KEYBOARD = 0x02
    const val STATE_FUNC = 0x03
}