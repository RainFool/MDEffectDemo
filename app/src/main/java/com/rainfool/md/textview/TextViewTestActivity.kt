package com.rainfool.md.textview

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_drawing_cache_demo.*
import kotlinx.android.synthetic.main.activity_text_view_test.*
import kotlin.system.measureTimeMillis

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


        mTestTextView.maxLines = 2
        mTestTextView.ellipsize = android.text.TextUtils.TruncateAt.END
        mBtnSetNegativeOne.setOnClickListener {
            mTestTextView.text = "joigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioew"
            mTestTextView.measure(0, 0)

            addViewToContainer()

        }
    }

    private fun addViewToContainer() {
        val view = TextView(this)
        view.text = "joigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioewjoigjeiwjgioew"

        view.maxLines = 2
        view.ellipsize = android.text.TextUtils.TruncateAt.END
        view.measure(0, 0)

        container.addView(view)
    }

}