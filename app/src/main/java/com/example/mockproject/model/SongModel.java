package com.example.mockproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class SongModel {

    private long idSong;
    private String nameSong;
    private String uriSong;
    private String singerSong;

    private String duration;



    public SongModel(long idSong, String nameSong, String uriSong, String singerSong) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.uriSong = uriSong;
        this.singerSong = singerSong;
    }

    public SongModel(long idSong, String nameSong, String uriSong, String singerSong, String duration) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.uriSong = uriSong;
        this.singerSong = singerSong;
        this.duration = duration;
    }

    public SongModel() {
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getUriSong() {
        return uriSong;
    }

    public void setUriSong(String uriSong) {
        this.uriSong = uriSong;
    }



    public String getSingerSong() {
        return singerSong;
    }

    public void setSingerSong(String singerSong) {
        this.singerSong = singerSong;
    }

    @Override
    public String toString() {
        return "SongModel{" +
                "idSong=" + idSong +
                ", nameSong='" + nameSong + '\'' +
                ", uriSong='" + uriSong + '\'' +
                ", singerSong='" + singerSong + '\'' +
                '}';
    }

    public long getIdSong() {
        return idSong;
    }

    public void setIdSong(long idSong) {
        this.idSong = idSong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongModel songModel = (SongModel) o;
        return idSong == songModel.idSong && Objects.equals(nameSong, songModel.nameSong) && Objects.equals(uriSong, songModel.uriSong) && Objects.equals(singerSong, songModel.singerSong);
    }

    public static DiffUtil.ItemCallback<SongModel> songModelItemCallback = new DiffUtil.ItemCallback<SongModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull SongModel oldItem, @NonNull SongModel newItem) {
            return oldItem.idSong == newItem.idSong ;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SongModel oldItem, @NonNull SongModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
