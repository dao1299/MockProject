package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.album_fragment;

public class AlbumModel {
    private String idAlbum;
    private String nameAlbum;
    private String nameArtist;
    private int numSong;


    public AlbumModel(String idAlbum, String nameAlbum, String nameArtist, int numSong) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.nameArtist = nameArtist;

        this.numSong = numSong;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    @Override
    public String toString() {
        return "AlbumModel{" +
                "idAlbum='" + idAlbum + '\'' +
                ", nameAlbum='" + nameAlbum + '\'' +
                ", nameArtist='" + nameArtist + '\'' +
                ", numSong=" + numSong +
                '}';
    }

    public int getNumSong() {
        return numSong;
    }

    public void setNumSong(int numSong) {
        this.numSong = numSong;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }
}
