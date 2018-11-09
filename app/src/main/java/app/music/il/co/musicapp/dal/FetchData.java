package app.music.il.co.musicapp.dal;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.music.il.co.musicapp.bll.Album;
import app.music.il.co.musicapp.bll.Song;

public class FetchData extends AsyncTask<Void, Void, Void> {
    private OnFinishGettingData listener;
    private List<Album> albums;

    public FetchData(List<Album> albums){
        this.albums = albums;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url = new URL("https://api.myjson.com/bins/hylvy");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while(line != null){
                line = rd.readLine();
                stringBuilder.append(line);
            }
            JSONArray allJSON = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < allJSON.length(); i++) {
                JSONObject albumJSON = (JSONObject) allJSON.get(i);
                JSONArray songsArray = albumJSON.getJSONArray("songs");
                ArrayList<Song> songs = new ArrayList<>();

                Album album = new Album();
                for (int j = 0; j < songsArray.length() ; j++) {
                    JSONObject songJSON = (JSONObject) songsArray.get(j);
                    songs.add(new Song(songJSON.getString("song"),
                            songJSON.getString("duration"),
                            songJSON.getString("#"),
                            songJSON.getString("url"),
                            album));
                }
                album.setAlbumName(albumJSON.getString("album"));
                album.setArtist(albumJSON.getString("artist"));
                album.setAlbumImage(albumJSON.getString("image"));
                album.setGenre(albumJSON.getString("genre"));
                album.setSongs(songs);
                albums.add(album);

            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.finishGettingData();
    }
    public void setOnFinishGettingDataListener(OnFinishGettingData listener){
        this.listener = listener;
    }
    public interface OnFinishGettingData{
        void finishGettingData();
    }
}
