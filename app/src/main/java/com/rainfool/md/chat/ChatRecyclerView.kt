package com.rainfool.md.chat

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 消息列表
 * @author krystian
 */
class ChatRecyclerView : RecyclerView {

    private var isVisible = true

    private var isScrollingToBottom = false

    private var needScrollToBottomWhenVisible = false

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    isScrollingToBottom = false
                }
            }
        })
    }

    fun scrollToBottom(smooth: Boolean) {
        val adapter = adapter ?: return
        val layoutManager = layoutManager as? LinearLayoutManager ?: return

        if (isVisible) {
            if (smooth) {
                isScrollingToBottom = true
                this.post {
                    layoutManager.smoothScrollToPosition(this, State(), (adapter.itemCount - 1).coerceAtLeast(0))
                }
            } else {
                layoutManager.scrollToPositionWithOffset((adapter.itemCount - 1).coerceAtLeast(0), 0)
            }
        } else {
            needScrollToBottomWhenVisible = true
        }
    }

    fun isBottom(): Boolean {
        val layoutManager = layoutManager as? LinearLayoutManager ?: return false
        val adapter = adapter ?: return false
        return layoutManager.findLastVisibleItemPosition() >= adapter.itemCount - 1
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

}