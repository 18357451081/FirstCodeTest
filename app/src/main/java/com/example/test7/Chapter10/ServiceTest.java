package com.example.test7.Chapter10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceTest extends Service {
    public ServiceTest() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
