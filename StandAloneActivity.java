package com.example.sachet.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandAloneActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);

        //different ways of creating onclicklisteners

        Button btnPlayVedio = findViewById(R.id.btnPlayVedio);
        Button btnPlayList = findViewById(R.id.btnPlayList);

        btnPlayVedio.setOnClickListener(this);
        btnPlayList.setOnClickListener(this);

//        View.OnClickListener ourListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        };
//        btnPlayList.setOnClickListener(ourListener);
//        btnPlayVedio.setOnClickListener(ourListener);
//
//        btnPlayList.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View view){
//
//            }
//
//        });
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.btnPlayVedio:
                intent = YouTubeStandalonePlayer.createVideoIntent(this,YoutubeActivity.GOOGLE_API_KEY,YoutubeActivity.YOUTUBE_VEDIO_ID,0,true,false);
                break;
            case R.id.btnPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this,YoutubeActivity.GOOGLE_API_KEY,YoutubeActivity.YOUTUBE_PLAYLIST,0,0,true,true);
                break;
            default:

        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}
