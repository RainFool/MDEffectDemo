package com.rainfool.md.fakenotification

import android.view.View
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.rainfool.md.common.RobolectricPowerMockCommon
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 *
 * @author krystian
 */
//@PrepareForTest(FakeNotificationContainer::class, View::class, FakeNotificationSender::class)
internal class FakeNotificationSenderTest : RobolectricPowerMockCommon() {

//    @Rule
//    @JvmField
//    var rule = PowerMockRule()


    private lateinit var contentView: View
    private lateinit var container: FakeNotificationContainer
    private lateinit var listener: FakeNotificationContainer.IFakeNotificationDismissListener

    @BeforeEach
    override fun setUp() {
        super.setUp()
        listener = mockk()
        contentView = mockk()
        container = mockk(relaxed = true)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testAddTask() {

        val sender = FakeNotificationSender(container)
//        Mockito.`when`(container.addContentView(contentView)).doAnswer {
//            sender.onFakeNotificationDismiss()
//        }
        val listener = object : FakeNotificationSender.IGenerateContentViewListener {
            override fun generateContentView() = contentView

        }
        val task = FakeNotificationSender.SenderTask(System.currentTimeMillis(), 0, listener, 0)
        sender.addTask(task)
        verify(container, times(1)).addContentView(contentView)
    }
}