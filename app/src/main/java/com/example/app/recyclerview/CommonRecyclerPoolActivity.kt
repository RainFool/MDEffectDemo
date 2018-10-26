package com.example.app.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.app.R

class CommonRecyclerPoolActivity : AppCompatActivity() {

    private lateinit var mRecyclerView:RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comon_recycler_pool)
        mRecyclerView = findViewById(R.id.rv_common_recycler_pool)
        mRecyclerView.adapter = SimpleNumberAdapter(100)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.recycledViewPool = CustomRecyclerViewPool
    }
}
