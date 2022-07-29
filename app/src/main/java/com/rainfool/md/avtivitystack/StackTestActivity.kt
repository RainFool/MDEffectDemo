package com.rainfool.md.avtivitystack

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.rainfool.md.R

class StackTestActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "StackTestActivity"
    }

    lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_test)
        Log.i(TAG, "onCreate: ")
        mHandler = Handler(Looper.getMainLooper())

        val btn = findViewById<Button>(R.id.btn_skip)
        btn.setOnClickListener { skipActivity() }

        val btnFinish = findViewById<Button>(R.id.btn_finish)
        btnFinish.setOnClickListener {
            finishSelf()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    private fun skipActivity() {
        Log.i(TAG, "skip A Activity clicked")
        mHandler.postDelayed({ skipA() }, 2000)
    }

    private fun skipA() {
        Log.i(TAG, "skip A Activity")
        val intent = Intent(this, StackAActivity::class.java)
        startActivity(intent)
//        mHandler.post { finish() }
    }

    private fun finishSelf() {
        Log.i(TAG, "finishSelf clicked")
        mHandler.postDelayed({ finish() }, 2000)
    }
}
