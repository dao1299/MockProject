package com.example.mockproject.model;

public class SongModel {
    private String nameSong;
    private String uriSong;
    private String singerSong;

    public SongModel(String nameSong, String uriSong, String singerSong) {
        this.nameSong = nameSong;
        this.uriSong = uriSong;
        this.singerSong = singerSong;
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
                "nameSong='" + nameSong + '\'' +
                ", uriSong='" + uriSong + '\'' +
                ", singerSong='" + singerSong + '\'' +
                '}';
    }
}
