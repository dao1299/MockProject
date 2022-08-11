package com.example.mockproject.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mockproject.R;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.view.main_activity.adapter.BindingExtension;

import java.util.ArrayList;
import java.util.List;

public class PlayMediaService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    private static final String TAG = "PlayMediaService";
//    private final IBinder playerBinder = new MusicBinder();
    private static List<SongModel> songModelList = new ArrayList<>();


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
        Log.i(TAG, "onCreate: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SongModel songModel = songsRepo.getSongCurrent();
        Log.i(TAG, "onStartCommand: "+songModel);
        initNotification(songModel);
//        mediaPlayer = MediaPlayer.create(this, Uri.parse(songModel.getUriSong()));
        if (mediaPlayer != null) mediaPlayer.release();
        playSong(songModel.getUriSong());
        return START_STICKY;
    }

    private void initNotification(SongModel songModel) {
//        Intent intentActivity = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.layout_notification);
        remoteView.setTextViewText(R.id.txtNameSongNoti,songModel.getNameSong());
        remoteView.setTextViewText(R.id.txtNameArtistNoti,songModel.getArtistSong());
        Bitmap bitmap = BindingExtension.getBitmap(songModel.getUriSong());
        if (bitmap==null){
            remoteView.setImageViewResource(R.id.imgThumbSongNoti,R.drawable.ic_song);
        }else{
            remoteView.setImageViewBitmap(R.id.imgThumbSongNoti,bitmap);
        }

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

    private void playSong(String uri){
        mediaPlayer = MediaPlayer.create(this,Uri.parse(uri));
        mediaPlayer.start();
    }

}
