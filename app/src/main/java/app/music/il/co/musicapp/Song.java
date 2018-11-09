package app.music.il.co.musicapp;

public class Song {
    private String songName;
    private String duration;
    private String orderNumber;
    private String songUrl;
    private Album album;

    public Song(String songName, String duration, String orderNumber, String songUrl, Album album) {
        this.songName = songName;
        this.duration = duration;
        this.orderNumber = orderNumber;
        this.songUrl = songUrl;
        this.album = album;
    }

    public String getSongName() {
        return songName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
