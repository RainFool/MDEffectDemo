package com.rainfool.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SubProcessService extends Service {
    public SubProcessService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}