package com.rainfool.md.fakenotification

/**
 * 仿通知队列管理
 * @author krystian
 */
class FakeNotificationSender(fakeNotificationContainer: FakeNotificationContainer) {

    fun addTask(senderTask: SenderTask) {

    }

    data class SenderTask(val submitTimestamp: Long)
}