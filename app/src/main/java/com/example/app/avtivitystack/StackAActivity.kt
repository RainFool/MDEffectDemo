package com.example.app.avtivitystack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app.BaseActivity
import com.example.app.R

class StackAActivity : BaseActivity() {

    init {
        Log.d(tag, "constructor: ")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_a)
    }
}
