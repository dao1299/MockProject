package com.example.mockproject.repository;

import com.example.mockproject.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongsRepo {
    List<SongModel> songModelList;
    int kindOfListSongs = -1;
    int currentSongIndex = -1;
    boolean isRepeat = false;
    boolean isShuffle = false;

    private static volatile SongsRepo instance = null;

    private SongsRepo(){
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
}
