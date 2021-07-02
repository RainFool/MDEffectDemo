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
        if (curTask == null) {
            consumeTask()
        }
    }

    fun removeTaskByType(type: Int) {
        val iterator = minTaskHeap.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (next.type == type) {
                iterator.remove()
            }
        }
    }

    override fun onFakeNotificationDismiss() {
        curTask = null
        consumeTask()
    }

    private fun consumeTask() {
        val task = nextValidTask() ?: return
        val view = task.listener?.generateContentView() ?: return
        container.addContentView(view)
        curTask = task
    }

    private fun nextValidTask(): SenderTask? {
        while (minTaskHeap.isNotEmpty()) {
            val task = minTaskHeap.poll()
            if (!task.isValid()) {
                continue
            }
            return task
        }
        return null
    }

    data class SenderTask(
        val submitTimestamp: Long,
        var timeoutTimeStamp: Long = 0,
        var listener: IGenerateContentViewListener? = null,
        var type: Int = 0
    ) :
        Comparable<SenderTask> {

        override fun compareTo(other: SenderTask): Int = submitTimestamp.toInt() - other.submitTimestamp.toInt()

        fun isValid(): Boolean {
            if (listener == null) {
                return false
            }
            if (isTimeOut()) {
                return false
            }
            return true
        }

        private fun isTimeOut(): Boolean {
            return if (timeoutTimeStamp <= 0) {
                false
            } else {
                System.currentTimeMillis() > timeoutTimeStamp
            }
        }
    }

    interface IGenerateContentViewListener {
        fun generateContentView(): View
    }
}