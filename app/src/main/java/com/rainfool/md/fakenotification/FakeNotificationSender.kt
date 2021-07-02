package com.rainfool.md.fakenotification

import android.view.View
import java.util.*

/**
 * 仿通知队列管理
 * @author krystian
 */
class FakeNotificationSender(private val container: FakeNotificationContainer) :
    FakeNotificationContainer.IFakeNotificationDismissListener {

    private val minTaskHeap = PriorityQueue<SenderTask>()

    private var curTask: SenderTask? = null

    init {
        container.setOnDismissListener(this)
    }

    fun addTask(senderTask: SenderTask) {
        minTaskHeap.offer(senderTask)
        if (curTask != null) {
            consumeTask()
        }
    }

    override fun onFakeNotificationDismiss() {
        curTask = null
        consumeTask()
    }

    private fun consumeTask() {
        val task = minTaskHeap.poll()
        val view = task.listener.generateContentView()
        container.addContentView(view)
        curTask = task
    }

    data class SenderTask(val submitTimestamp: Long, val type: Int, val listener: IGenerateContentViewListener)

    interface IGenerateContentViewListener {
        fun generateContentView(): View
    }
}