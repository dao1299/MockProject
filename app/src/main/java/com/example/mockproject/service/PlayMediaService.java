package com.example.mockproject.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mockproject.R;
import com.example.mockproject.app.MyApplication;
import com.example.mockproject.model.SongModel;
import com.example.mockproject.repository.SongsRepo;
import com.example.mockproject.view.main_activity.adapter.BindingExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayMediaService extends Service {

    public static final String NOTIFICATION_CHANNEL_ID = "notification_channel";
    private static final String TAG = "PlayMediaService";
    SongsRepo songsRepo = SongsRepo.getInstance();
    SingletonMedia singletonMedia = SingletonMedia.getInstance();
    MediaPlayer mediaPlayer = singletonMedia.mediaPlayer;
    private List<SongModel> songModelList = new ArrayList<>();
    private int currentIndex = 0;
    private SongModel songCurrent;
    private Boolean isPlaying = false;
    private Boolean isShuffle = false;
    private Boolean isRepeat = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        songModelList = songsRepo.getSongModelList();
        currentIndex = songsRepo.getCurrentSongIndex();
        Log.i(TAG, "onCreate: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isShuffle = songsRepo.isShuffle();
        isRepeat = songsRepo.isRepeat();
        songModelList = songsRepo.getSongModelList();
        if (currentIndex != songsRepo.getCurrentSongIndex()) {
            Log.i(TAG, "onStartCommand: changeSong");
            isPlaying = false;
            mediaPlayer.release();
            mediaPlayer = null;
        }
        currentIndex = songsRepo.getCurrentSongIndex();
        songCurrent = songModelList.get(currentIndex);
        Log.i(TAG, "onStartCommand: currnet: " + songCurrent);
        if (intent != null && intent.getAction() != null)
            handleAction(intent.getAction());
        else
            playPauseSong();
        return START_STICKY;
    }

    private void initNotification(int iconPlayPause) {
        Bitmap bitmap = BindingExtension.getBitmap(songCurrent.getUriSong());

        Intent playPauseButtonIntent = new Intent(this, Broadcast.class).setAction(MyApplication.PLAY_PAUSE_SONG);
        PendingIntent playPausePending = PendingIntent.getBroadcast(this, 0, playPauseButtonIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent previousButtonIntent = new Intent(this, Broadcast.class).setAction(MyApplication.PREVIOUS_SONG);
        PendingIntent previousPending = PendingIntent.getBroadcast(this, 0, previousButtonIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent nextButtonIntent = new Intent(this, Broadcast.class).setAction(MyApplication.NEXT_SONG);
        PendingIntent nextPending = PendingIntent.getBroadcast(this, 0, nextButtonIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent finishButtonIntent = new Intent(this, Broadcast.class).setAction(MyApplication.FINISH_SERVICE);
        PendingIntent finishPending = PendingIntent.getBroadcast(this, 0, finishButtonIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(getApplication(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_song_checked)
                .setContentTitle(songCurrent.getNameSong())
                .setContentText(songCurrent.getArtistSong())
                .setLargeIcon(bitmap)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .addAction(R.drawable.ic_previous_song, "previous", previousPending)
                .addAction(iconPlayPause, "play_pause", playPausePending)
                .addAction(R.drawable.ic_next_song, "next", nextPending)
                .addAction(R.drawable.ic_close_button, "finish", finishPending)
                .setSound(null)
                .build();

        startForeground(1, notification);
    }

    private void handleAction(String action) {
        switch (action) {
            case MyApplication.PLAY_PAUSE_SONG:
                playPauseSong();
                break;
            case MyApplication.PREVIOUS_SONG:
                previousSong();
                break;
            case MyApplication.NEXT_SONG:
                nextSong();
                break;
            case MyApplication.FINISH_SERVICE:
                finishService();
                break;
            case MyApplication.UPDATE_CURRENT_DURATION:
                updateCurrent();
                break;
        }
    }

    private void playPauseSong() {
        Log.i(TAG, "playPauseSong: ");
        if (isPlaying) {
            mediaPlayer.pause();
            initNotification(R.drawable.ic_play);
            isPlaying = false;


        } else {
            if (mediaPlayer == null)
                mediaPlayer = MediaPlayer.create(this, Uri.parse(songCurrent.getUriSong()));
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nextSong();
                }
            });
            isPlaying = true;
            initNotification(R.drawable.ic_pause);
        }
        songsRepo.getSongModelMutableLiveData().postValue(songCurrent);
        songsRepo.getPlayPauseMutable().postValue(isPlaying);
    }

    private void finishService() {
        Log.i(TAG, "finishService: ");
        stopSelf();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void nextSong() {
        Log.i(TAG, "nextSong: ");
//        previousIndex = currentIndex;
        if (isShuffle) {
            Random random = new Random();
            currentIndex = random.nextInt(songModelList.size());
        } else {
            if (currentIndex == songModelList.size() - 1) currentIndex = 0;
            else currentIndex++;
        }

        songCurrent = songModelList.get(currentIndex);
        songsRepo.setCurrentSongIndex(currentIndex);
        isPlaying = false;
        mediaPlayer.release();
        mediaPlayer = null;
        playPauseSong();
    }

    private void previousSong() {
        Log.i(TAG, "previousSong: ");
        if (currentIndex == 0)
            currentIndex = songModelList.size() - 1;
        else
            currentIndex--;
        songsRepo.setCurrentSongIndex(currentIndex);
        songCurrent = songModelList.get(currentIndex);
        isPlaying = false;
        mediaPlayer.release();
        mediaPlayer = null;
        playPauseSong();
    }

    private void updateCurrent() {
        Log.i(TAG, "updateCurrent: "+mediaPlayer.getCurrentPosition());
        songsRepo.getCurrentDuration().postValue(Long.valueOf(mediaPlayer.getCurrentPosition()));
        Log.i(TAG, "=========================");
    }

}
