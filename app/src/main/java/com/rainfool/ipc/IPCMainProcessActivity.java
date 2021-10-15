package com.rainfool.ipc;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rainfool.md.R;

public class IPCMainProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipcmain_process);
    }

    public void onOpenSubProcessActClicked(View view) {
        Intent intent = new Intent(this, IPCSubProcessActivity.class);
        startActivity(intent);
    }
}