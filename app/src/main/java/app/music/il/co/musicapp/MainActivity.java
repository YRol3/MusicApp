package app.music.il.co.musicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private Custom_Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar loadingProgress;
    private ArrayList<Album> albums = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
    public void initialize(){
        mRecyclerView = findViewById(R.id.recycler_View);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadingProgress = findViewById(R.id.progressBar);
        FetchData backgroundProcess = new FetchData(albums);
        backgroundProcess.setOnFinishGettingDataListener(new FetchData.OnFinishGettingData() {
            @Override
            public void finishGettingData() {
                OnFinishedBuildingJSON();
            }
        });
        backgroundProcess.execute();
    }
    public void OnFinishedBuildingJSON(){
        loadingProgress.setVisibility(View.GONE);
        mAdapter = new Custom_Adapter(albums, this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
