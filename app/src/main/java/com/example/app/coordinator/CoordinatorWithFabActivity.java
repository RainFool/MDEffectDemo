package com.example.app.coordinator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.app.R;

public class CoordinatorWithFabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_with_fab);
    }

    public void onFabClick(View view) {

        Snackbar.make(getWindow().getDecorView(), "Snackbar", Snackbar.LENGTH_SHORT).show();
    }
}
