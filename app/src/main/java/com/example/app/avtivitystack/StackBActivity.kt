package com.example.app.avtivitystack

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.app.BaseActivity
import com.example.app.R

class StackBActivity : BaseActivity() {

    init {
        Log.d(tag, "constructor: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_b)

        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val info  = manager.appTasks
    }
}
