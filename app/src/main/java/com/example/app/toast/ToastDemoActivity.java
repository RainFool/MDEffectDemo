package com.example.app.toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.app.BaseApp;
import com.example.app.R;

public class ToastDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_demo);
    }

    public void onActivityContextClick(View view) {
        Toast.makeText(ToastDemoActivity.this, "Toast from activity context", Toast.LENGTH_SHORT).show();
    }

    public void onBaseAppContextClick(View view) {
        Toast.makeText(BaseApp.gContext, "Toast from app context", Toast.LENGTH_SHORT).show();
    }
}
