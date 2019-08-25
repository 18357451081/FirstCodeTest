package com.example.test7.Chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test7.R;

public class NetActivity extends AppCompatActivity {
    Button bWebView,bHttpURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        bWebView = findViewById(R.id.bWebView);
        bHttpURL = findViewById(R.id.bHttpURL);
        bWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NetActivity.this,WebViewTest.class);
                startActivity(intent);
            }
        });
        bHttpURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NetActivity.this,HttpURLTest.class);
                startActivity(intent);
            }
        });

    }
}
