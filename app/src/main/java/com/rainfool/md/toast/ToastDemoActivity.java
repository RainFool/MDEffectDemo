package com.rainfool.md.toast;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rainfool.md.BaseApp;
import com.rainfool.md.R;

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
