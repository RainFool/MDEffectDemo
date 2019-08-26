package com.rainfool.md.inflater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rainfool.md.R

class LayoutInflaterActivity : AppCompatActivity() {

    private lateinit var mInflater : LayoutInflater;

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

    fun inflater1() : View {
        return mInflater.inflate(R.layout.layout_inflater_test,null)

    }
}
