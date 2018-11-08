package app.music.il.co.musicapp;

public class Songs {
    private String songName;
    private String duration;
    private String orderNumber;
    private String songUrl;
    private Album album;

    public Songs(String songName, String duration, String orderNumber, String songUrl, Album album) {
        this.songName = songName;
        this.duration = duration;
        this.orderNumber = orderNumber;
        this.songUrl = songUrl;
        this.album = album;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
