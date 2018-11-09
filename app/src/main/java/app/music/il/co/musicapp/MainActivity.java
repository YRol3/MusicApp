package app.music.il.co.musicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.music.il.co.musicapp.bll.Album;
import app.music.il.co.musicapp.bll.adapters.Custom_Adapter;
import app.music.il.co.musicapp.dal.FetchData;
import app.music.il.co.musicapp.pl.LoadingDialog;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private Custom_Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Album> albums = new ArrayList<>();
    private LoadingDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize(){
        progressDialog = new LoadingDialog(this);
        progressDialog.createLoadingDialog(getString(R.string.loading_title), getString(R.string.loading_message));
        progressDialog.show();
        mRecyclerView = findViewById(R.id.recycler_View);
        mRecyclerView.setVisibility(View.GONE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
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
        progressDialog.hide();
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new Custom_Adapter(albums, this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
