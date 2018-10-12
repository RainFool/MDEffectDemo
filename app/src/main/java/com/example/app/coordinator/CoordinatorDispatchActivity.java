package com.example.app.coordinator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoordinatorDispatchActivity extends AppCompatActivity {

    RecyclerView mRvDispatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_dispatch);
        mRvDispatchList = findViewById(R.id.rv_coordinator_dispatch_list);
        mRvDispatchList.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.HORIZONTAL));
        mRvDispatchList.setAdapter(new DispatchAdapter(CoordinatorDispatchActivity.this));
    }

    private static class DispatchAdapter extends RecyclerView.Adapter<DispatchHolder> {

        private static final HashMap<Class<? extends Activity>, String> DISPATCHING_ACTIVITIES_MAP = new HashMap<>();

        static {
            DISPATCHING_ACTIVITIES_MAP.put(CoordinatorWithFabActivity.class, CoordinatorWithFabActivity.class.getSimpleName());
            DISPATCHING_ACTIVITIES_MAP.put(CoordinatorAppbarActivity.class, CoordinatorAppbarActivity.class.getSimpleName());
            DISPATCHING_ACTIVITIES_MAP.put(LogBehaviorActivity.class, LogBehavior.class.getSimpleName());
        }

        private List<String> mDescriptionList = new ArrayList<>(DISPATCHING_ACTIVITIES_MAP.values());
        private List<Class<? extends Activity>> mClazzList = new ArrayList<>(DISPATCHING_ACTIVITIES_MAP.keySet());

        private LayoutInflater mInflater;

        private Context mContext;

        public DispatchAdapter(Context context) {
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public DispatchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.coordinator_dispatch_item, parent, false);
            return new DispatchHolder(view);
        }

        @Override
        public void onBindViewHolder(DispatchHolder holder, final int position) {
            holder.textView.setText(mDescriptionList.get(position));
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, mClazzList.get(position));
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return DISPATCHING_ACTIVITIES_MAP.size();
        }
    }

    private static class DispatchHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public DispatchHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_dispatch_title);
        }
    }
}
