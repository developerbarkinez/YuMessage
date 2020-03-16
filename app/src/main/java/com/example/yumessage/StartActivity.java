package com.example.yumessage;

import android.content.Intent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button login, register;
    VideoView video1,video2,video3;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null){
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });
        video1 = findViewById(R.id.video4);
        String URL = "https://media.giphy.com/media/gHhm12Zh6fCuszfUxe/giphy.mp4";
        Uri uri = Uri.parse(URL);
        video1.setVideoURI(uri);
        video1.start();
        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                try {
                    if (mp.isPlaying()) {
                        mp.setLooping(true);
                        mp.stop();
                        mp.release();
                        mp = new MediaPlayer();
                    }
                    mp.setVolume(0f, 0f);
                    mp.setLooping(true);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
