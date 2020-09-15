package com.example.tudienanhviet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity implements
    YouTubePlayer.OnInitializedListener {
        private YouTubePlayerView ytpv;
        private final String API_KEY_YT = "AIzaSyAY2s_O9_ITTiCdzwUQjEq3csMsf8VtOec";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_you_tube);
            ytpv = (YouTubePlayerView) findViewById(R.id.ytpv);
            ytpv.initialize(API_KEY_YT, this);
        }
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer ytp, boolean b) {
            // https://www.youtube.com/watch?v=T0sHaz4H9MQ
            if (!b) {
                Intent i=getIntent();
                String url=i.getStringExtra("url");
               if(url!=null) {
                   Log.i("yt",url);
                   ytp.cueVideo(url);
               }
               else
                   Log.i("yt","Sai");
            }
        }
        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
            if (result.isUserRecoverableError()) {
                result.getErrorDialog(this, 1).show();
            } else {
                String error = String.format("Error initializing YouTube player", result.toString());
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        }

}