package com.example.notificationtest;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    PendingIntent pendingIntent;
    int hour;
    int minute;
    Calendar c1;


    final String LOG_TAG = "MyLogs";
    AlarmManager alarmManager;
    public void onCreate(){
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"onStarCommand");
        hour = intent.getIntExtra("t1",1);
        minute = intent.getIntExtra("t2",1);
        someTask();

        return super.onStartCommand(intent, flags, startId);
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }
    public IBinder onBind(Intent intent){
        return null;
    }
    public void someTask() {
        final Intent myIntent = new Intent(getApplicationContext(), Work.class);
        Date currentDate1 = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH", Locale.getDefault());
        DateFormat timeFormat2 = new SimpleDateFormat("mm", Locale.getDefault());
        int currentHour1 = Integer.parseInt(timeFormat.format(currentDate1));
        int currentMinute1 = Integer.parseInt(timeFormat2.format(currentDate1));

                while((currentHour1 != hour) | (currentMinute1 != minute)) {
                    currentDate1 = new Date();
                    currentHour1 = Integer.parseInt(timeFormat.format(currentDate1));
                    currentMinute1 = Integer.parseInt(timeFormat2.format(currentDate1));
                    //      alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    //    pendingIntent = PendingIntent.getBroadcast(MyService.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //  alarmManager.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(), pendingIntent);
                    if ((currentHour1!= hour) | (currentMinute1 != minute)) {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }


        addNotification1();

    }
    public void addNotification1 (){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setAutoCancel(true);

        Intent intent1 = new Intent(MyService.this, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(MyService.this,0, new Intent[]{intent1},PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);





        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);


        NotificationCompat.Builder builder1 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("Title change")
                        .setContentText("Notification text change");

        Notification notification1 = builder.build();

        NotificationManager notificationManager1 =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager1.notify(1, notification1);


    }

}