package com.rainfool.md.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rainfool.md.R;

public class BroadcastTestActivity extends AppCompatActivity {

    private IntentFilter mFilter;
    private HomeBroadcastReceiver mRecevier;

    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mRecevier = new HomeBroadcastReceiver();

        mRegisterButton = findViewById(R.id.btn_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i < 5000;i++) {
                    Log.d("rainfool", "onClick: " + i);
                    //重复注册广播再华为手机上崩溃
                    HomeBroadcastReceiver receiver = new HomeBroadcastReceiver();
                    BroadcastTestActivity.this.registerReceiver(receiver,mFilter);
                    // 如果反注册也不会有问题
//                    BroadcastTestActivity.this.unregisterReceiver(receiver);
                }
            }
        });
    }


    class HomeBroadcastReceiver extends BroadcastReceiver {

        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null) {

                }
            }
        }
    }
}
