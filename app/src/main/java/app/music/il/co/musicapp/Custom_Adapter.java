package app.music.il.co.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.My_View_Holder> {
    private ArrayList<Album> albums;
    private Context context;
    private MP3Player mp3Player;
    private ImageView currentState;
    private static Album playingAlbum;
    private ArrayList<ImageView> controlsList = new ArrayList<>();
    public Custom_Adapter(ArrayList<Album> albums, Context context){
        this.albums = albums;
        this.context = context;
        mp3Player = new MP3Player(context);
    }
    @NonNull
    @Override
    public My_View_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.screen_recycler_view, viewGroup, false);
        return new My_View_Holder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull final My_View_Holder my_view_holder, int i) {
        Picasso.get().load(albums.get(i).getAlbumImage()).resize(300,300).into(my_view_holder.img, new Callback() {
            @Override
            public void onSuccess() {
                my_view_holder.imgLoadingProgress.setVisibility(View.GONE);
            }
            @Override
            public void onError(Exception e) {

            }
        });
        setAllButOne(currentState);
        if(albums.get(i) != playingAlbum) {
            my_view_holder.pauseBtn.setVisibility(View.GONE);
            my_view_holder.playBtn.setVisibility(View.GONE);
        }
        Small_Recycler_Adapter small_adapter = new Small_Recycler_Adapter(albums.get(i).getSongs(), context);
        my_view_holder.rv.setAdapter(small_adapter);
        small_adapter.setItemOnClickListener(new Small_Recycler_Adapter.OnClickItem() {
            @Override
            public void OnClickItemPosition(int position, View v) {
                //Toast.makeText(context, "song number: " + position + "in album number: "+my_view_holder.getAdapterPosition() , Toast.LENGTH_SHORT).show();
                if(mp3Player.play(albums.get(my_view_holder.getAdapterPosition()).getSongs().get(position).getSongUrl())) {
                    setAllButOne(my_view_holder.pauseBtn);
                }
                else {
                    setAllButOne(my_view_holder.playBtn);
                }
                playingAlbum = albums.get(my_view_holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class My_View_Holder extends  RecyclerView.ViewHolder{
        ImageView img;
        ImageView playBtn;
        ImageView pauseBtn;
        RecyclerView rv;
        ProgressBar imgLoadingProgress;
        public My_View_Holder(@NonNull View view) {
            super(view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            img = view.findViewById(R.id.albumImage);
            imgLoadingProgress = view.findViewById(R.id.imgProgressBar);
            pauseBtn = view.findViewById(R.id.pause);
            playBtn = view.findViewById(R.id.play);
            playBtn.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.GONE);
            controlsList.add(pauseBtn);
            controlsList.add(playBtn);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(albums.get(getAdapterPosition()) == playingAlbum) {
                        if (mp3Player.toggle())
                            setAllButOne(pauseBtn);
                        else
                            setAllButOne(playBtn);
                    }
                }
            });
            rv = view.findViewById(R.id.rv);
            SnapHelper helper = new LinearSnapHelper();
            rv.setLayoutManager(layoutManager);
            helper.attachToRecyclerView(rv);
        }
    }
    public void setAllButOne(ImageView except){
        if(except != null) {
            except.setVisibility(View.VISIBLE);
            currentState = except;
            for (int i = 0; i < controlsList.size(); i++) {
                if (controlsList.get(i) != except) controlsList.get(i).setVisibility(View.GONE);
            }
        }
    }
}
