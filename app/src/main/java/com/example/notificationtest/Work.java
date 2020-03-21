package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import androidx.core.app.NotificationCompat;

public class Work extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG_LOG","12324421242");
    }
}
