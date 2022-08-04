package com.example.mockproject.model;

public class PlaylistModel {
    private String namePlaylist;
    private String uriPlaylist;
    private String singerPlaylist;

    public PlaylistModel(String namePlaylist, String uriPlaylist, String singerPlaylist) {
        this.namePlaylist = namePlaylist;
        this.uriPlaylist = uriPlaylist;
        this.singerPlaylist = singerPlaylist;
    }

    public PlaylistModel() {
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public String getUriPlaylist() {
        return uriPlaylist;
    }

    public void setUriPlaylist(String uriPlaylist) {
        this.uriPlaylist = uriPlaylist;
    }

    public String getSingerPlaylist() {
        return singerPlaylist;
    }

    public void setSingerPlaylist(String singerPlaylist) {
        this.singerPlaylist = singerPlaylist;
    }

    @Override
    public String toString() {
        return "PlaylistModel{" +
                "namePlaylist='" + namePlaylist + '\'' +
                ", uriPlaylist='" + uriPlaylist + '\'' +
                ", singerPlaylist='" + singerPlaylist + '\'' +
                '}';
    }
}
