package com.rainfool.md.textview

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.rainfool.md.R
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
        mBtnSetNegativeOne.setOnClickListener {
            testStart()
//            testSetBaseProp()
        }
    }

    private fun testStart() {
        val timeSetText = measureTimeMillis { testSetText() }

        val timeSetBaseProp = measureTimeMillis { testSetBaseProp() }


        Log.i(TAG, "testSetText takes time:$timeSetText ms")
        Log.i(TAG, "timeSetBaseProp takes time:$timeSetBaseProp ms")
    }

    private fun testSetText() {
        mTestTextView.setText("joigjeiwjgioew")
    }

    private fun testSetBaseProp() {
        TextViewParams().bindViewInner(mTestTextView)
    }

}