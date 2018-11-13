package com.rainfool.md.inflater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.rainfool.md.R
import hugo.weaving.DebugLog

class LayoutInflaterActivity : AppCompatActivity() {

    private lateinit var mInflater : LayoutInflater;

    @DebugLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TimeWatcher.onMethodEntry("setContentView")
        setContentView(R.layout.activity_layout_inflater)
        TimeWatcher.onMethodExit("setContentView")

        TimeWatcher.onMethodEntry("inflater1")
        mInflater =  LayoutInflater.from(this)
        TimeWatcher.onMethodExit("inflater1")

        inflater1()
    }

    @DebugLog
    fun inflater1() : View {
        return mInflater.inflate(R.layout.layout_inflater_test,null)

    }
}
