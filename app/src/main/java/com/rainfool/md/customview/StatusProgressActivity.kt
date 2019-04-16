package com.rainfool.md.customview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_status_progress.*
import java.util.*
import java.util.concurrent.TimeUnit

class StatusProgressActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "StatusProgressActivity"
    }

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

        val stringFormat = getString(R.string.status_progress_count_down_format)

        val millisInFuture = TimeUnit.MINUTES.toMillis(90)
        val countDownInterval = TimeUnit.MILLISECONDS.toMillis(100)
        val countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                Log.d(TAG, "onFinish")
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d(TAG, "onTick:$millisUntilFinished")
                var left = millisUntilFinished
                val hours = TimeUnit.MILLISECONDS.toHours(left)
                left -= TimeUnit.HOURS.toMillis(hours)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(left)
                left -= TimeUnit.MINUTES.toMillis(minutes)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(left)
                left -= TimeUnit.SECONDS.toMillis(seconds)
                val millis = TimeUnit.MILLISECONDS.toMillis(left)
                val tip = String.format(stringFormat, hours, minutes, seconds, millis)
                mStatusProgressView.setCurrentTip(mCurStep, tip)

            }

        }
        countDownTimer.start()


    }
}
