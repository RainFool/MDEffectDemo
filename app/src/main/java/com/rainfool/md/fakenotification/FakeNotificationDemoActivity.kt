package com.rainfool.md.fakenotification

import android.os.Bundle
import android.view.LayoutInflater
import com.rainfool.md.BaseActivity
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_top_sheet_demo.*

/**
 * @author krystian
 */
internal class FakeNotificationDemoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_sheet_demo)

        val testView = LayoutInflater.from(this).inflate(R.layout.top_sheet_demo_item, null)
        btnAdd.setOnClickListener {
            topSheetView.addViewContent(testView)
        }
    }
}