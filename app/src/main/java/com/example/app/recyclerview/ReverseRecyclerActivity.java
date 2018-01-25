package com.example.app.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rainfool
 * @date 2018/1/16
 */

public class ReverseRecyclerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Button mButton1, mButton2;
    private SimpleStringAdapter mAdapter;
    private List<String> mDataList;

    private List<String> mTempList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_recycler);
        mButton1 = findViewById(R.id.btn_action1);
        mButton2 = findViewById(R.id.btn_action2);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAction1Click();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAction2Click();
            }
        });
        mRecyclerView = findViewById(R.id.rv_reverse);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layout);
        mDataList = new ArrayList<>();
        mDataList.add("first ");
        mAdapter = new SimpleStringAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        initTempList();
    }

    private void initTempList() {
        mTempList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mTempList.add("第" + i);
        }
    }

    private void onAction1Click() {
        mDataList.addAll(0, mTempList);
        mAdapter.notifyItemRangeInserted(0, mTempList.size());
//        mAdapter.notifyDataSetChanged();
    }

    private void onAction2Click() {
        mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);

    }
}
