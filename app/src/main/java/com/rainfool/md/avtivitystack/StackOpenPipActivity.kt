package com.rainfool.md.avtivitystack

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_stack_open_pip.*

class StackOpenPipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_open_pip)

        btn_open_pip.setOnClickListener {
            val intent = Intent()
            intent.component = ComponentName("com.example.android.pictureinpicture", "com.example.android.pictureinpicture.MainActivity")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}