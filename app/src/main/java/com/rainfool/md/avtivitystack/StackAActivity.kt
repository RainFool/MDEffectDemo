package com.rainfool.md.avtivitystack

import android.os.Bundle
import android.util.Log
import com.rainfool.md.BaseActivity
import com.rainfool.md.R

class StackAActivity : BaseActivity() {

    init {
        Log.d(tag, "constructor: ")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_a)
    }
}
