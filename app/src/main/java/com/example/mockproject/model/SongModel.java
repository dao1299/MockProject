package com.example.mockproject.model;

public class SongModel {
    private long idSong;
    private String nameSong;
    private String artistSong;
    private String artistId;
    private String albumSong;
    private String albumId;
    private String durationSong;
    private String uriSong;
    private String genreSong;
    private String genreId;


    public SongModel(long idSong, String nameSong, String artistSong, String artistId, String albumSong, String albumId, String durationSong, String uriSong, String genreSong, String genreId) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.artistSong = artistSong;
        this.artistId = artistId;
        this.albumSong = albumSong;
        this.albumId = albumId;
        this.durationSong = durationSong;
        this.uriSong = uriSong;
        this.genreSong = genreSong;
        this.genreId = genreId;

    }

    public SongModel() {
    }

    public SongModel(long idSong, String nameSong, String artistSong, String uriSong) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.artistSong = artistSong;
        this.uriSong = uriSong;
    }

    public long getIdSong() {
        return idSong;
    }

    public void setIdSong(long idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getArtistSong() {
        return artistSong;
    }

    public void setArtistSong(String artistSong) {
        this.artistSong = artistSong;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getAlbumSong() {
        return albumSong;
    }

    public void setAlbumSong(String albumSong) {
        this.albumSong = albumSong;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getDurationSong() {
        return durationSong;
    }

    public void setDurationSong(String durationSong) {
        this.durationSong = durationSong;
    }

    public String getUriSong() {
        return uriSong;
    }

    public void setUriSong(String uriSong) {
        this.uriSong = uriSong;
    }

    public String getGenreSong() {
        return genreSong;
    }

    public void setGenreSong(String genreSong) {
        this.genreSong = genreSong;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "SongModel{" +
                "idSong=" + idSong +
                ", nameSong='" + nameSong + '\'' +
                ", artistSong='" + artistSong + '\'' +
                ", artistId='" + artistId + '\'' +
                ", albumSong='" + albumSong + '\'' +
                ", albumId='" + albumId + '\'' +
                ", durationSong='" + durationSong + '\'' +
                ", uriSong='" + uriSong + '\'' +
                ", genreSong='" + genreSong + '\'' +
                ", genreId='" + genreId + '\'' +
                '}';
    }
}
