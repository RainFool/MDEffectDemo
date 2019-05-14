package com.rainfool.kotlin.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rainfool.kotlin.coroutines.callback.CallbackError
import com.rainfool.kotlin.coroutines.callback.DataCallback
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_coroutines_demo.*

class CoroutinesDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_demo)

        mStartRequestButton.setOnClickListener {
            mResultTextView.text = "开始请求"
            mProgressBar.visibility = View.VISIBLE
            UserSerivece.getInstance().getUserProfile(object : DataCallback<String>() {
                override fun onResponse(rsp: String?, extra: Any?) {
                    mProgressBar.visibility = View.GONE
                    mResultTextView.text = "请求完成"
                }

                override fun onError(callbackError: CallbackError) {
                    mProgressBar.visibility = View.GONE
                    mResultTextView.text = "请求错误"
                }
            })
        }
    }
}
