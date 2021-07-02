package com.rainfool.md.fakenotification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.rainfool.md.BaseActivity
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_top_sheet_demo.*
import kotlinx.android.synthetic.main.top_sheet_demo_item.*

/**
 * @author krystian
 */
internal class FakeNotificationDemoActivity : BaseActivity() {

    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_sheet_demo)
        val sender = FakeNotificationSender(topSheetView)

        btnAdd.setOnClickListener {
            ++num
            val senderTask = FakeNotificationSender.SenderTask(
                System.currentTimeMillis()
            ).apply {
                timeoutTimeStamp = 0
                type = 0
                listener = GenerateContentView(num)
            }
            sender.addTask(senderTask)
        }
        btnAdd2.setOnClickListener {
            ++num
            val senderTask = FakeNotificationSender.SenderTask(
                System.currentTimeMillis()
            ).apply {
                timeoutTimeStamp = 0
                type = 2
                listener = GenerateContentView(num)
            }
            sender.addTask(senderTask)
        }

        btnRemove.setOnClickListener {
            sender.removeTaskByType(0)
        }
    }

    inner class GenerateContentView(private val num: Int) : FakeNotificationSender.IGenerateContentViewListener {
        override fun generateContentView(): View {
            val testView: View = LayoutInflater.from(this@FakeNotificationDemoActivity)
                .inflate(R.layout.top_sheet_demo_item, null)
            val tvText = testView.findViewById<TextView>(R.id.tvTest)
            tvText.setText(num.toString())
            return testView
        }
    }
}