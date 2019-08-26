package com.example.test7.Chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test7.R;

public class Chapter10 extends AppCompatActivity {
    public static final int UPDATA_TEXT = 1;
    Button bService;
    TextView text;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATA_TEXT:{
                    text.setText("子线程调用修改");
                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter10);
        bService = findViewById(R.id.service_button);
        text = findViewById(R.id.service_text);
        bService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATA_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
