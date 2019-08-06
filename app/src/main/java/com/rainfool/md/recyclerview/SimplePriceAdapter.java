package com.rainfool.md.recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rainfool.md.R;

import java.util.List;

/**
 * @author rainfool
 * @date 2018/1/16
 */
public class SimplePriceAdapter extends RecyclerView.Adapter<SimplePriceAdapter.SimpleNumberViewHolder> {

    private static final String TAG = "SimpleNumberAdapter";

    private List<String> mDataList;

    public SimplePriceAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
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
        holder.setString(mDataList.get(position));
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }


    public class SimpleNumberViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public SimpleNumberViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_simple_demo);
        }

        public void setNum(int i) {
            textView.setText(i + "");
        }

        public void setString(String s) {
            textView.setText(getAdapterPosition() + ":" + s);
        }
    }
}
