package com.rainfool.md.coordinator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rainfool.md.R;

public class CoordinatorWithFabActivity extends AppCompatActivity {

    View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_with_fab);
        mRootView = findViewById(R.id.root_view);
    }

    public void onFabClick(View view) {

        Snackbar.make(mRootView, "Snackbar", Snackbar.LENGTH_SHORT).show();
    }
}
