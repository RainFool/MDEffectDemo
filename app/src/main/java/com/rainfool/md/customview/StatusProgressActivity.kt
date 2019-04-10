package com.rainfool.md.customview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_status_progress.*

class StatusProgressActivity : AppCompatActivity() {

    private var mCurStep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_progress)

        mBtnPlus.setOnClickListener {
            mStatusProgressView.setCurrentStep(++mCurStep)
        }
        mBtnMinus.setOnClickListener {
            mStatusProgressView.setCurrentStep(--mCurStep)
        }
        mBtnZero.setOnClickListener {
            mCurStep = 0
            mStatusProgressView.setCurrentStep(mCurStep)
        }
    }
}
