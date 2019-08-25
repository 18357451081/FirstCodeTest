package com.example.test7.Chapter8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.test7.R;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PlayVideoTest extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    Button bplay,bpause,breplay;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_test);
        bplay = findViewById(R.id.videoPlay);
        bpause = findViewById(R.id.videoPause);
        breplay = findViewById(R.id.videoReplay);
        videoView = findViewById(R.id.videoView);
        getPermission();
        initVideoPlayer();
        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!videoView.isPlaying()){
                    videoView.start();
                }
            }
        });
        bpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });
        breplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.resume();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }

    private void initVideoPlayer(){
        try{
            File file = new File("/storage/emulated/0/Movies/706_1_0_x264.mp4");
            if(file.exists()){
                Toast.makeText(PlayVideoTest.this,file.getName(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(PlayVideoTest.this,"文件不存在",Toast.LENGTH_SHORT).show();
            }
            videoView.setZOrderOnTop(true);
//            videoView.setZOrderMediaOverlay(true);
            videoView.setVideoPath(file.getPath());

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
