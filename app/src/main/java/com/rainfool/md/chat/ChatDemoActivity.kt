package com.rainfool.md.chat

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rainfool.md.R
import com.rainfool.md.recyclerview.SimpleStringAdapter
import kotlinx.android.synthetic.main.activity_chat_demo.*

class ChatDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_demo)
        val list = (0..100).map { it.toString() }
        chatListView.adapter = SimpleStringAdapter(list)
        chatListView.layoutManager = ChatLinearLayoutManager(this)

        topArea.setOnClickListener {
            if (topArea.measuredHeight <= resources.getDimensionPixelOffset(R.dimen.dp40)) {
                topArea.layoutParams = topArea.layoutParams.apply {
                    height = resources.getDimensionPixelOffset(R.dimen.dp180)
                }
//                chatListView.scrollToBottom(false)
            } else {
                topArea.layoutParams = topArea.layoutParams.apply {
                    height = resources.getDimensionPixelOffset(R.dimen.dp40)
                }
            }
        }
    }
}