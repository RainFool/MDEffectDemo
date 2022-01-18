package com.rainfool.md.softinput

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.rainfool.md.R
import com.tencent.mobileqq.partyhouse.im.input.NormalInputView
import java.util.ArrayList

/**
 * fitSystemWindow为false的时候，这个搭配adjustResize和View.SYSTEM_UI_FLAG_HIDE_NAVIGATION可以保证键盘和表情面板的行为
 */
class SoftInputTestActivity : AppCompatActivity() {
    var listView: ListView? = null
    var editText: NormalInputView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_input_test)
        window.setSoftInputMode(window.attributes.softInputMode or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        var viewFlag: Int = window.decorView.systemUiVisibility

        viewFlag = viewFlag or View.SYSTEM_UI_FLAG_FULLSCREEN
        viewFlag = viewFlag or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        viewFlag = viewFlag or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        viewFlag = viewFlag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        viewFlag = viewFlag or View.SYSTEM_UI_FLAG_IMMERSIVE
        window.decorView.systemUiVisibility = viewFlag

        listView = findViewById(R.id.lv_content)
        editText = findViewById(R.id.et_input)
        listView?.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
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