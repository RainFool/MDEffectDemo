package com.example.app.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;

/**
 * @author rainfool
 * @date 2018/1/16
 */
public class SimpleNumberAdapter extends RecyclerView.Adapter<SimpleNumberAdapter.SimpleNumberViewHolder> {

    private static final String TAG = "SimpleNumberAdapter";

    private int count = 5;

    public SimpleNumberAdapter(int count) {
        this.count = count;
    }

    @Override
    public SimpleNumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: sub");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_demo_sub, parent, false);
        return new SimpleNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleNumberViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: sub" + position);
        holder.setNum(position);
    }

    @Override
    public int getItemCount() {

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }


    public class SimpleNumberViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public SimpleNumberViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void setNum(int i) {
            textView.setText(i + "");
        }
    }
}
