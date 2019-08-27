package com.rainfool.md.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainfool.md.R;

/**
 * @author rainfool
 */
public class RecyclerViewDemoActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewDemo";

    RecyclerView mContainerRv;

    RecyclerView.RecycledViewPool mRecycledViewPool = CustomRecyclerViewPool.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        mContainerRv = findViewById(R.id.rv_container);
        mContainerRv.setLayoutManager(new LinearLayoutManager(this));
        mContainerRv.setAdapter(new ContainerAdapter());
        mContainerRv.setRecycledViewPool(mRecycledViewPool);

        findViewById(R.id.btn_skip_common_pool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewDemoActivity.this, CommonRecyclerPoolActivity.class);
                startActivity(intent);
            }
        });
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
            SimpleNumberAdapter adapter = new SimpleNumberAdapter(position,5);
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
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            mainItemRecyclerView.setLayoutManager(layoutManager);
            mainItemRecyclerView.setRecycledViewPool(mRecycledViewPool);
        }
    }

}
