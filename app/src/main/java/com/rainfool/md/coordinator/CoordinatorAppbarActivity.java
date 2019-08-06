package com.rainfool.md.coordinator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainfool.md.R;
import com.rainfool.md.recyclerview.SimpleNumberAdapter;

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
