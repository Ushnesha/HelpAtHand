package com.example.android.helphand;

import android.content.Intent;
import android.os.IBinder;

/**
 * Created by darip on 21-05-2017.
 */

public interface Service {
    public IBinder onBind(Intent intent);
}
