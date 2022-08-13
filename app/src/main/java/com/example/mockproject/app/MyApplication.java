package com.example.mockproject.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    public static final String PREVIOUS_SONG = "PREVIOUS_SONG";
    public static final String NEXT_SONG = "NEXT_SONG";
    public static final String PLAY_PAUSE_SONG = "PLAY_PAUSE_SONG";
    public static final String FINISH_SERVICE = "FINISH_SERVICE";
    public static final String UPDATE_CURRENT_DURATION = "UPDATE_CURRENT_DURATION";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notification Service",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setSound(null,null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager!=null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
