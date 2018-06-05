package com.example.app.coordinator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.app.R;
import com.example.app.recyclerview.SimpleNumberAdapter;

public class CoordinatorAppbarActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_appbar);
        mRecyclerView = findViewById(R.id.rv_coordinator_app_bar_content);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(CoordinatorAppbarActivity.this));
        mRecyclerView.setAdapter(new SimpleNumberAdapter(100));
    }
}
