package app.music.il.co.musicapp;

import java.util.ArrayList;

public class Album {
    private String albumName;
    private String artist;
    private String albumImage;
    private String genre;
    private ArrayList<Songs> songs;

    public Album() {
    }
    public Album(String albumName, String artist, String albumImage, String genre, ArrayList<Songs> songs) {
        this.albumName = albumName;
        this.artist = artist;
        this.albumImage = albumImage;
        this.genre = genre;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Songs> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Songs> songs) {
        this.songs = songs;
    }


}
