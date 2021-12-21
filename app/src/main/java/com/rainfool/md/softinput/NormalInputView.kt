package com.tencent.mobileqq.partyhouse.im.input

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import com.rainfool.md.R
import com.rainfool.md.softinput.InputViewStateConst.STATE_FUNC
import com.rainfool.md.softinput.InputViewStateConst.STATE_KEYBOARD

/**
 * 目前默认的input bar
 * @author krystian
 */
class NormalInputView : BaseInputView {

    companion object {
        private const val TAG = "NormalInputView"
    }

    private var editText: EditText? = null
    private var sendView: View? = null
    private var emojiBtn: View? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun getInputEditText(): EditText? {
        return editText
    }

    override fun getSendBtn(): View? {
        return sendView
    }

    override fun getEmojiBtn(): View? {
        return emojiBtn
    }

    override fun getLayoutId(): Int {
        return R.layout.input_normal
    }

    override fun onLayoutInflated() {
        editText = findViewById(R.id.input_et)
        sendView = findViewById(R.id.send_tv)
        emojiBtn = findViewById(R.id.emoji_iv)

        emojiBtn?.setOnClickListener {
            if (currentState == STATE_FUNC) {
                currentState = STATE_KEYBOARD
            } else {
                currentState = STATE_FUNC
            }
        }
    }

}