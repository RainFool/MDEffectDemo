package com.example.app.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.app.R;

/**
 * @author rainfool
 * @date 2018/1/16
 */

public class ReverseRecyclerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_recycler);
        mRecyclerView = findViewById(R.id.rv_reverse);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(new SimpleNumberAdapter(5));
    }
}
