package com.example.test7.Chapter8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test7.R;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PlayAudioTest extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    Button bplay,bpause,bstop;
    TextView text;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio_test);
        bplay = findViewById(R.id.paly);
        bpause = findViewById(R.id.pause);
        bstop = findViewById(R.id.stop);
        text = findViewById(R.id.audioText);
        getPermission();
        initMediaPlayer();
        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(PlayAudioTest.this,Environment.getExternalStorageDirectory().toString(),Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                if(mediaPlayer.isPlaying()){
                    text.setText("播放");
                }
            }
        });

        bpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                text.setText("暂停");
            }
        });
        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                text.setText("停止");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void initMediaPlayer(){
        try{
            File file = new File("/storage/emulated/0/kuss/rewind/download/netease/凯瑟喵 - 血腥爱情故事（Cover：张惠妹）.mp3");
            if(file.exists()){
                Toast.makeText(PlayAudioTest.this,file.getName(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(PlayAudioTest.this,"文件不存在",Toast.LENGTH_SHORT).show();
            }

            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getPermission(){
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(EasyPermissions.hasPermissions(this,permissions)){

        }else {
            EasyPermissions.requestPermissions(this,"申请权限",1,permissions);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"权限申请成功的回调",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"权限申请拒绝的回调",Toast.LENGTH_SHORT).show();
    }
}
