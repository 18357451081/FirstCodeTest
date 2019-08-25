package com.example.test7.Chapter8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test7.R;

public class MediaActivity extends AppCompatActivity {
    Button audio,video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        audio = findViewById(R.id.palyAudio);
        video = findViewById(R.id.palyVideo);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this,PlayAudioTest.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this,PlayVideoTest.class);
                startActivity(intent);
            }
        });
    }
}
