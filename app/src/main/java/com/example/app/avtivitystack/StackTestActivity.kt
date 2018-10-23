package com.example.app.avtivitystack

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.app.R

class StackTestActivity : AppCompatActivity() {

    lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_test)

        mHandler = Handler(Looper.getMainLooper())

        mHandler.postDelayed({skipA()},200)

        val btn = findViewById<Button>(R.id.btn_skip)
        btn.setOnClickListener { _ -> skipActivity() }
    }

    private fun skipActivity() {


        fun skipB() {
            val intent = Intent(this, StackBActivity::class.java)
            startActivity(intent)
        }

        skipA()
    }

    fun skipA() {
        val intent = Intent(this, StackAActivity::class.java)
        startActivity(intent)
    }
}
