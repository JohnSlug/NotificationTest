package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.WallpaperInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static android.text.TextUtils.concat;

public class MainActivity<notification, timePicker, clockTime> extends AppCompatActivity {
    Button b1;
    Button b2;
    TextView clockTime;
    TimePicker timePicker;
    PendingIntent pending_intent;
    TextClock currentTime;
    Calendar c1;
    private WallpaperInfo mContext;
   // Uri defaultSoundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + mContext.getPackageName() + "/raw/mysound");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timePicker = findViewById(R.id.timePicker);
       // currentTime = findViewById(R.id.timeText);
        clockTime = findViewById(R.id.clockTime);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                clockTime = findViewById(R.id.clockTime);
                //addNotification();
                clockTime.setText(AlarmTime());
               onClickStart(v);
            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                onClickStop(v);
            }
        });
    }
    public void onClickStop(View v){

        stopService(new Intent(this, MyService.class));
    }
    public void onClickStart(View v){
        timePicker = findViewById(R.id.timePicker);


        startService(new Intent(this, MyService.class).putExtra("t1",timePicker.getHour()).putExtra("t2",timePicker.getMinute()));

    }
    public String AlarmTime(){
        timePicker = findViewById(R.id.timePicker);
       // c1.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
        // c1.set(Calendar.MINUTE,timePicker.getMinute());

        Integer alarmHours = timePicker.getHour();
        Integer alarmMinutes = timePicker.getMinute();
        String stringAlarmMinutes = "Time:";

        if (alarmMinutes<10){
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        }else{
            stringAlarmMinutes = alarmMinutes.toString();
        }
        String stringAlarmTime;



        if(alarmHours>12){
            alarmHours = alarmHours - 12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes);
        }else{
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes);
        }
        return stringAlarmTime;
    }
    public void addNotification (){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setAutoCancel(true);

    Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivities(MainActivity.this,0, new Intent[]{intent1},PendingIntent.FLAG_UPDATE_CURRENT);
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
