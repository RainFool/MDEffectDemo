package com.rainfool.md.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rainfool.md.R

class CommonRecyclerPoolActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: androidx.recyclerview.widget.RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comon_recycler_pool)
        mRecyclerView = findViewById(R.id.rv_common_recycler_pool)
        mRecyclerView.adapter = SimpleNumberAdapter(100)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        mRecyclerView.setRecycledViewPool(CustomRecyclerViewPool)
    }
}
