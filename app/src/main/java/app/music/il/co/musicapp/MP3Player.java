package app.music.il.co.musicapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MP3Player {
    private Context context;
    MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private String lastURL;
    MP3Player(Context context){
        this.context = context;
    }

    public boolean play(String url){
        if(url != lastURL) {
            Uri myUri = Uri.parse(url);
            try {
                if (mediaPlayer != null) mediaPlayer.stop();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(context, myUri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        if(isPlaying)mediaPlayer.start();
                    }
                });
                isPlaying=true;
                mediaPlayer.prepareAsync();
                lastURL = url;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return toggle();
        }
        return isPlaying;
    }
    public boolean toggle(){
        if(isPlaying){
            if(mediaPlayer.isPlaying()) mediaPlayer.pause();
            isPlaying=false;
        }else{
            if(mediaPlayer.getAudioSessionId()!=0)mediaPlayer.start();
            isPlaying=true;
        }
        return isPlaying;
    }
}
