package com.rainfool.md.popup;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.rainfool.md.R;

public class PopupTestActivity extends Activity {

    private static final String TAG = "PopupTestActivity";

    RecyclerView mRecyclerView;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: " + hasFocus);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_test);
        mRecyclerView = findViewById(R.id.rv_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new PopupHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(position + "");
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }

    private class PopupHolder extends RecyclerView.ViewHolder {

        public PopupHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(v);
                }
            });
        }

    }

    private void showPopup(View v) {
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        int flags = getWindow().getAttributes().flags;

        boolean isDrawSystemBar = (flags & WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) == 0;
        Log.d(TAG, "showPopup: isDrawSystemBar: " + isDrawSystemBar);


        Log.d(TAG, "showPopup,v top:" + getRectInWindow(v).top + ",screen height:" + screenHeight);

        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.layout_popup_text, null);
        TextView textView = rootView.findViewById(R.id.tv_in_popup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "tv in popup click", Toast.LENGTH_LONG).show();
            }
        });
        PopupWindow popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.MATCH_PARENT,
                -3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(0);
        int bottom = screenHeight - getRectInWindow(v).top;
        Log.d(TAG, "final bottom:" + bottom);
//        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 1460);
        popupWindow.showAsDropDown(v, 0, 60);
    }

    private static Rect getRectInWindow(View view) {
        final int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
    }
}