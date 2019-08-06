package com.rainfool.md.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.widget.TextView
import com.rainfool.md.R

class LogBehaviorActivity : AppCompatActivity() {

    private val TAG = "LogBehaviorActivity"

    lateinit var mTvTarget : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_behavior)
        mTvTarget = findViewById(R.id.tv_log_behavior)
        val params = mTvTarget.layoutParams
        if (params is androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) {
            params.behavior = LogBehavior()
        }
    }


}

