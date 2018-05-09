package com.example.app.nestscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.app.R;
import com.example.app.recyclerview.SimpleNumberAdapter;

/**
 * @author rainfool
 */
public class RecyclerViewInScrollViewActivity extends AppCompatActivity {

    Button mDisableButton, mEnableButton;
    LockableScrollView mScrollView;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_in_scroll_view);

        initViews();
        setupRecyclerView();
        setupButtons();
    }

    private void initViews() {
        mDisableButton = findViewById(R.id.btn_disable_scrolling);
        mEnableButton = findViewById(R.id.btn_enable_scrolling);
        mScrollView = findViewById(R.id.sv_container);
        mRecyclerView = findViewById(R.id.rv_recycler_in_scroll);
    }

    private void setupRecyclerView() {
        mRecyclerView.setAdapter(new SimpleNumberAdapter(50));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupButtons() {
        mDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.setScrollingEnabled(false);
            }
        });
        mEnableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.setScrollingEnabled(true);
            }
        });
    }


}