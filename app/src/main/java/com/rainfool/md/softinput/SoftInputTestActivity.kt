package com.rainfool.md.softinput

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.rainfool.md.R
import com.tencent.mobileqq.partyhouse.im.input.NormalInputView
import java.util.ArrayList

class SoftInputTestActivity : AppCompatActivity() {
    var listView: ListView? = null
    var editText: NormalInputView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_input_test)
        val contentView = findViewById<FrameLayout>(android.R.id.content)
        contentView.fitsSystemWindows = true
        contentView.clipToPadding = true
        listView = findViewById(R.id.lv_content)
        editText = findViewById(R.id.et_input)
        listView?.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, data))
    }

    private val data: List<String>
        private get() {
            val data: MutableList<String> = ArrayList()
            data.add("测试数据1")
            data.add("测试数据2")
            data.add("测试数据3")
            data.add("测试数据4")
            return data
        }
}