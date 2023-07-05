package com.rainfool.md.textview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_drawing_cache_demo.*
import kotlinx.android.synthetic.main.activity_text_view_test.*

/**
 *
 * Created by rainfool on 2019/4/25.
 */
class TextViewTestActivity : Activity() {

    companion object {
        private const val TAG = "TextViewTestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_test)
        mBtnSetNegativeOne.setOnClickListener {
            val intent = Intent(it.context, HuyaUdbTestActivity::class.java)
            startActivity(intent)
        }
    }

}