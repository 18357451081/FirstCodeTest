package com.example.test7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test7.Chapter10.Chapter10;
import com.example.test7.Chapter7.RunTime;
import com.example.test7.Chapter8.MediaActivity;
import com.example.test7.Chapter8.NotificationTest;
import com.example.test7.Chapter9.NetActivity;

public class MainActivity extends AppCompatActivity {
    Button runTime,notification,mediaPlay,net,service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTime = findViewById(R.id.runTime);
        notification = findViewById(R.id.notification);
        mediaPlay = findViewById(R.id.mediaPlay);
        net = findViewById(R.id.net);
        service = findViewById(R.id.chapter10);
        runTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RunTime.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationTest.class);
                startActivity(intent);
            }
        });
        mediaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MediaActivity.class);
                startActivity(intent);
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NetActivity.class);
                startActivity(intent);
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Chapter10.class);
                startActivity(intent);
            }
        });
    }

}
