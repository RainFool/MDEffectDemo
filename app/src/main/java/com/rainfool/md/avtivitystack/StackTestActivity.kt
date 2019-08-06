package com.rainfool.md.avtivitystack

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.rainfool.md.R

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
