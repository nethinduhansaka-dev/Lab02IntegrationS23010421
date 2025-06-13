package com.s23010421.vpnhansaka;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private EditText etStudentID, etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etStudentID = findViewById(R.id.etStudentID);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);



        // Set up background audio
        setupBackgroundAudio();

        // Set up login button click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simple validation
                if (validateInput()) {
                    // Navigate to Maps Activity
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupVideo() {
        // Set the path to the video file in the raw folder
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.sample_video;
        Uri uri = Uri.parse(videoPath);

        // Set up the video view
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });
    }

    private void setupBackgroundAudio() {
        // Create and start the media player for background audio
        mediaPlayer = MediaPlayer.create(this, R.raw.background_audio);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private boolean validateInput() {
        // Simple validation for demonstration purposes
        if (etStudentID.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your Student ID", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.start();
        }

        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}