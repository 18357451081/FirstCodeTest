package com.example.test12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Random;

public class NumberActivity extends AppCompatActivity {
    public static final String NUMBER_NAME = "number_name";
    public static final String NUMBER_IMAGE_ID = "number_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        Intent intent = getIntent();
        String numberName = intent.getStringExtra(NUMBER_NAME);
        int numberImageId = intent.getIntExtra(NUMBER_IMAGE_ID,0);
        Toolbar toolbar = findViewById(R.id.toolbar_view);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView numberImageView = findViewById(R.id.number_image_view);
        TextView numberContentText = findViewById(R.id.number_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(numberName);
        Glide.with(this).load(numberImageId).into(numberImageView);
        String numberContent = generateNumberContent(numberName);
        numberContentText.setText(numberContent);
    }
    private String generateNumberContent(String numberName){
        StringBuilder numberContent = new StringBuilder();
        Random random = new Random();
        int maxInt = random.nextInt(950) + 50;
        for(int i = 0; i<maxInt; i++){
            if(i == (maxInt -1)) numberContent.append("共有：" + i + "个");
            numberContent.append(numberName);
        }
        return numberContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
