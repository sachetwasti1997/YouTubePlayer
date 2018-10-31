package com.example.sachet.youtubeplayer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener {
    public static final String TAG = "MainActivity";
    public static final String GOOGLE_API_KEY = "AIzaSyCRaznmhKhALUwyztWtsc9tq854ZF1T4Xo";
    public static final String YOUTUBE_VEDIO_ID = "kXYiU_JCYtU";
    public static final String YOUTUBE_PLAYLIST = "RDEMww6ZEHgLhQ-8eu_x7Z-FJw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //below ines of codes inflates the layout
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.activity_youtube);
        //another way of doing it is like
        ConstraintLayout constraintLayout = (ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_main,null);
        setContentView(constraintLayout);

//        Button button = new Button(this);
//        button.setLayoutParams(new ConstraintLayout.LayoutParams(1000,400));
//        button.setText("Button");
//        constraintLayout.addView(button);
        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(youTubePlayerView);
        youTubePlayerView.initialize(GOOGLE_API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is "+provider.getClass().toString());
        Toast.makeText(this,"Initialised successfully",Toast.LENGTH_SHORT).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored){
            youTubePlayer.cueVideo(YoutubeActivity.YOUTUBE_VEDIO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //Things to be done if there is a failure in the code
        final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        }else{
            String errorMessage = String.format("There was some error in the programme (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this,"Good the vedio is playing", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Vedio is paused",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onPaused: paused*****");
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Vedio is Stopped",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onStopped: stopped******");
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Ad has started now",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Vedio has started now",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onVideoStarted: started************");
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"Vedio has ended now",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onVideoEnded: ended*******");
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
