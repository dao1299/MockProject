package com.example.mockproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mockproject.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongsRepo {
    List<SongModel> songModelList;
    List<SongModel> allSong;
    int kindOfListSongs = -1;
    int currentSongIndex = 0;
    boolean isRepeat = false;
    boolean isShuffle = false;
    boolean isPlaying = false;
    boolean stateMedia = false;
    private MutableLiveData<SongModel> songModelMutableLiveData = new MutableLiveData<>();




    private static volatile SongsRepo instance = null;

    private SongsRepo(){
        allSong = new ArrayList<>();
        songModelList = new ArrayList<>();
    }
    public static SongsRepo getInstance(){
        if (instance==null){
            synchronized (SongsRepo.class){
                instance = new SongsRepo();
            }
        }
        return instance;
    }

    public List<SongModel> getAllSong() {
        return allSong;
    }

    public void setAllSong(List<SongModel> allSong) {
        this.allSong = allSong;
    }

    public List<SongModel> getSongModelList() {
        return songModelList;
    }

    public void setSongModelList(List<SongModel> songModelList) {
        this.songModelList = songModelList;
    }

    public int getKindOfListSongs() {
        return kindOfListSongs;
    }

    public void setKindOfListSongs(int kindOfListSongs) {
        this.kindOfListSongs = kindOfListSongs;
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }

    public SongModel getSongCurrent(){
        return songModelList.get(currentSongIndex);
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public boolean isShuffle() {
        return isShuffle;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isStateMedia() {
        return stateMedia;
    }

    public void setStateMedia(boolean stateMedia) {
        this.stateMedia = stateMedia;
    }

    public MutableLiveData<SongModel> getSongModelMutableLiveData() {
        return songModelMutableLiveData;
    }

    public void setSongModelMutableLiveData(MutableLiveData<SongModel> songModelMutableLiveData) {
        this.songModelMutableLiveData = songModelMutableLiveData;
    }
}
