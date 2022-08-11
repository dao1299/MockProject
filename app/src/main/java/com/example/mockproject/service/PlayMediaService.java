package com.example.mockproject.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mockproject.R;
import com.example.mockproject.app.MyApplication;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.view.main_activity.adapter.BindingExtension;

import java.util.ArrayList;
import java.util.List;

public class PlayMediaService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    private static final String TAG = "PlayMediaService";
    //    private final IBinder playerBinder = new MusicBinder();
    private List<SongModel> songModelList = new ArrayList<>();
    private int currentIndex = -1;
    private SongModel songCurrent;
    private Boolean isPlaying=false;


    SongsRepo songsRepo = SongsRepo.getInstance();

    SingletonMedia singletonMedia = SingletonMedia.getInstance();

    MediaPlayer mediaPlayer = singletonMedia.mediaPlayer;



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
        songModelList = songsRepo.getSongModelList();
        currentIndex = songsRepo.getCurrentSongIndex();
        Log.i(TAG, "onCreate: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        songModelList = songsRepo.getSongModelList();
//        currentIndex = songsRepo.getCurrentSongIndex();
        if (currentIndex != songsRepo.getCurrentSongIndex()){
            Log.i(TAG, "onStartCommand: changeSong");
            isPlaying = false;
            mediaPlayer.release();
            mediaPlayer = null;
        }
        currentIndex = songsRepo.getCurrentSongIndex();
        songCurrent = songModelList.get(currentIndex);
        Log.i(TAG, "onStartCommand: currnet: "+songCurrent);
//        Log.i(TAG, "onStartCommand: " + songModel);
//        initNotification(R.drawable.ic_song);
//        mediaPlayer = MediaPlayer.create(this, Uri.parse(songModel.getUriSong()));
//        if (mediaPlayer != null) mediaPlayer.release();
//        playPauseSong();
        if (intent!=null && intent.getAction()!=null)
            handleAction(intent.getAction());
        else
            playPauseSong();
        return START_STICKY;
    }

    private void initNotification(int iconPlayPause) {
//        Intent intentActivity = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);

//        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.layout_notification);
//        remoteView.setTextViewText(R.id.txtNameSongNoti, songCurrent.getNameSong());
//        remoteView.setTextViewText(R.id.txtNameArtistNoti, songCurrent.getArtistSong());
        Bitmap bitmap = BindingExtension.getBitmap(songCurrent.getUriSong());
//        if (bitmap == null) {
//            remoteView.setImageViewResource(R.id.imgThumbSongNoti, R.drawable.ic_song);
//        } else {
//            remoteView.setImageViewBitmap(R.id.imgThumbSongNoti, bitmap);
//        }

        Intent playPauseButtonIntent = new Intent(this,Broadcast.class).setAction(MyApplication.PLAY_PAUSE_SONG);
        PendingIntent playPausePending =PendingIntent.getBroadcast(this,0,playPauseButtonIntent,PendingIntent.FLAG_IMMUTABLE);

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
                .setSmallIcon(R.drawable.ic_song_checked)
                .setContentTitle(songCurrent.getNameSong())
                .setContentText(songCurrent.getArtistSong())
                .setLargeIcon(bitmap)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .addAction(R.drawable.ic_previous_song,"11123",playPausePending)
                .addAction(iconPlayPause,"12",playPausePending)
                .addAction(R.drawable.ic_next_song,"12443",playPausePending)
                .addAction(R.drawable.ic_close_button,"playpause",playPausePending)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }

    private void playPauseSong() {
        Log.i(TAG, "playPauseSong: ");
        if (isPlaying) {
            mediaPlayer.pause();
            initNotification(R.drawable.ic_play);
            isPlaying=false;
        }
        else{
            if (mediaPlayer!=null)
            {
                mediaPlayer.start();

            }else{
                mediaPlayer = MediaPlayer.create(this, Uri.parse(songCurrent.getUriSong()));
                mediaPlayer.start();
            }
            isPlaying=true;

            initNotification(R.drawable.ic_pause);
        }
    }



    private void handleAction(String action) {
        switch (action) {
            case MyApplication.PLAY_PAUSE_SONG:
                playPauseSong();
                break;
        }
    }


}
