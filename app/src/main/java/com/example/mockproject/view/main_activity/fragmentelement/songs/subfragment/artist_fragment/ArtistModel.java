package com.example.mockproject.view.main_activity.fragmentelement.songs.subfragment.artist_fragment;

public class ArtistModel {
    private String nameArtist;
    private String numAlbumsArtist;
    private String numSongsArtist;
    private int srcAvarArtist;

    public ArtistModel(String nameArtist, String numAlbumsArtist, String numSongsArtist, int srcAvarArtist) {
        this.nameArtist = nameArtist;
        this.numAlbumsArtist = numAlbumsArtist;
        this.numSongsArtist = numSongsArtist;
        this.srcAvarArtist = srcAvarArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getNumAlbumsArtist() {
        return numAlbumsArtist + " Albums";
    }

    public void setNumAlbumsArtist(String numAlbumsArtist) {
        this.numAlbumsArtist = numAlbumsArtist;
    }

    public String getNumSongsArtist() {
        return numSongsArtist + " Songs";
    }

    public void setNumSongsArtist(String numSongsArtist) {
        this.numSongsArtist = numSongsArtist;
    }

    public int getSrcAvarArtist() {
        return srcAvarArtist;
    }

    public void setSrcAvarArtist(int srcAvarArtist) {
        this.srcAvarArtist = srcAvarArtist;
    }

    @Override
    public String toString() {
        return "ArtistModel{" +
                "nameArtist='" + nameArtist + '\'' +
                ", numAlbumsArtist='" + numAlbumsArtist + '\'' +
                ", numSongsArtist='" + numSongsArtist + '\'' +
                ", srcAvarArtist=" + srcAvarArtist +
                '}';
    }
}
