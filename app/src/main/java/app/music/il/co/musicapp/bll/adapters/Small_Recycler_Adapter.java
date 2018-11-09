package app.music.il.co.musicapp.bll.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.music.il.co.musicapp.R;
import app.music.il.co.musicapp.bll.Song;

public class Small_Recycler_Adapter extends RecyclerView.Adapter<Small_Recycler_Adapter.My_Small_View_Holder> {

    private List<Song> songs;
    private OnClickItem listener;
    private Context context;
    public Small_Recycler_Adapter(List<Song> songs, Context context){
        this.songs = songs;
        this.context = context;
    }
    @NonNull
    @Override
    public My_Small_View_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new My_Small_View_Holder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull My_Small_View_Holder my_small_view_holder, int i) {
        my_small_view_holder.artistNsong.setText(String.format("%s %s %s",songs.get(i).getAlbum().getArtist(), context.getString(R.string.sing_artist_seperator), songs.get(i).getSongName()));
        my_small_view_holder.albumName.setText(String.format("%s : %s", context.getString(R.string.album_string), songs.get(i).getAlbum().getAlbumName()));
        my_small_view_holder.songDuration.setText(songs.get(i).getDuration());
        my_small_view_holder.songNumber.setText(String.format("%s%s", songs.get(i).getOrderNumber(), context.getString(R.string.hash_tag)));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class My_Small_View_Holder extends RecyclerView.ViewHolder {

        TextView artistNsong;
        TextView albumName;
        TextView songDuration;
        TextView songNumber;

        public My_Small_View_Holder(@NonNull View view) {
            super(view);
            artistNsong = view.findViewById(R.id.artist);
            albumName = view.findViewById(R.id.album);
            songDuration = view.findViewById(R.id.duration);
            songNumber = view.findViewById(R.id.number);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClickItemPosition(getAdapterPosition(), v);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnLongClickItemPosition(getAdapterPosition(), v);
                    return true;
                }
            });
        }
    }
    public void setItemOnClickListener(OnClickItem listener){
        this.listener = listener;
    }

    public interface OnClickItem{
        void OnClickItemPosition(int position, View v);
        void OnLongClickItemPosition(int position, View v);
    }
}
