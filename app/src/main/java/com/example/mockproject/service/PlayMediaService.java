package com.example.mockproject.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mockproject.R;
import com.example.mockproject.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class PlayMediaService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    private static final String TAG = "PlayMediaService";
//    private final IBinder playerBinder = new MusicBinder();
    private static List<SongModel> songModelList = new ArrayList<>();
    MediaPlayer mediaPlayer;



    SingletonMedia singletonMedia = SingletonMedia.getInstance();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    public class MusicBinder extends Binder{
//        public PlayMediaService getService(){
//            return PlayMediaService.this;
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        initNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SongModel songModel = (SongModel) intent.getSerializableExtra("song");
        Log.i(TAG, "onStartCommand: "+songModel);
//        mediaPlayer = MediaPlayer.create(this, Uri.parse(songModel.getUriSong()));
        if (singletonMedia.mediaPlayer ==null){
            singletonMedia.mediaPlayer = MediaPlayer.create(this, Uri.parse(songModel.getUriSong()));
            singletonMedia.mediaPlayer.start();
        }
        return START_STICKY;
    }

    private void initNotification() {
//        Intent intentActivity = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.layout_notification);
//        remoteView.setTextViewText(R.id.txtTitleNoti, song.getNameSong()+" - " +song.getSingerSong());


//        remoteView.setOnClickPendingIntent(R.id.btnPlayPauseNoti,getPendingIntent());

//        remoteView.setOnClickPendingIntent(R.id.btnNextNoti,getPendingIntent(3,song));
//        remoteView.setOnClickPendingIntent(R.id.btnPreviousNoti,getPendingIntent(4,song));
//        remoteView.setOnClickPendingIntent(R.id.btnClearServiceNoti,getPendingIntent(6,song));

//        if (mediaPlayer!=null && mediaPlayer.isPlaying()){
//            remoteView.setOnClickPendingIntent(R.id.btnPlayPauseNoti,getPendingIntent(1,song));
//            remoteView.setImageViewResource(R.id.btnPlayPauseNoti,R.drawable.pause);
//        }else{
//            remoteView.setOnClickPendingIntent(R.id.btnPlayPauseNoti,getPendingIntent(2,song));
//            remoteView.setImageViewResource(R.id.btnPlayPauseNoti,R.drawable.play);
//        }

        Notification notification = new NotificationCompat.Builder(getApplication(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(remoteView)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }

}
