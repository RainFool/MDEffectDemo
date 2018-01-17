package com.example.app.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewDemo";

    RecyclerView mContainerRv;

    RecyclerView.RecycledViewPool mRecycledViewPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        mContainerRv = findViewById(R.id.rv_container);
        mContainerRv.setLayoutManager(new LinearLayoutManager(this));
        mContainerRv.setAdapter(new ContainerAdapter());
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mContainerRv.setRecycledViewPool(mRecycledViewPool);

    }

    private class ContainerAdapter extends RecyclerView.Adapter<ContainerViewHolder> {


        @Override
        public ContainerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder: main");
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_recycler_demo_main, parent, false);
            return new ContainerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContainerViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: main" + position);
            SimpleNumberAdapter adapter = new SimpleNumberAdapter(5);
            holder.mainItemRecyclerView.setAdapter(adapter);

        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class ContainerViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mainItemRecyclerView;

        public ContainerViewHolder(View itemView) {
            super(itemView);
            mainItemRecyclerView = itemView.findViewById(R.id.rv_item_main);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setReverseLayout(true);
            mainItemRecyclerView.setLayoutManager(layoutManager);
            mainItemRecyclerView.setRecycledViewPool(mRecycledViewPool);
        }
    }

}
