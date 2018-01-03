package com.example.app.notifycation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.MainActivity;
import com.example.app.R;

public class NotificationTestActivity extends AppCompatActivity {


    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        mButton = (Button) findViewById(R.id.btn_send_notification);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendNotification();
                    }
                },2000);
            }
        });
    }

    private void sendNotification() {
        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("name")
                        .setContentText("content")
//                        .setLargeIcon(portrait)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        // 点击消失
                        .setAutoCancel(true)
                        // 设置该通知优先级
                        .setVibrate(new long[0])
                        .setDefaults(Notification.DEFAULT_LIGHTS)
                        .setTicker("ticker")
                        // 通知首次出现在通知栏，带上升动画效果的
                        .setWhen(System.currentTimeMillis());
        notifyBuilder.setPriority(Notification.PRIORITY_HIGH);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifyBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel((int) 23);
        mNotificationManager.notify((int) 23, notifyBuilder.build());
    }
}
