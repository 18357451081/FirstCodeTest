package com.example.test7.Chapter8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test7.R;

public class NotificationTest extends AppCompatActivity {
    Button sentNotice,camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.cancel(1);//取消通知

        sentNotice = findViewById(R.id.sendNotice);
        camera = findViewById(R.id.camera);
        sentNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager inmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String id = "my_channel_01";
                //老版本的
                Intent intent = new Intent(NotificationTest.this,NotificationTest.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTest.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//判断系统sdk版本,版本O为26,8.0
//                    Toast.makeText(NotificationTest.this,Build.VERSION.SDK_INT,Toast.LENGTH_SHORT).show();
                    //这段仅适用于Android8.0及以上
                    String name = "我是渠道名字";
                    NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                    // 开启指示灯，如果设备有的话
                    channel.enableLights(true);
                    // 设置指示灯颜色
                    channel.setLightColor(Color.GREEN);
                    // 是否在久按桌面图标时显示此渠道的通知
                    channel.setShowBadge(true);
                    // 设置是否应在锁定屏幕上显示此频道的通知
                    channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                    // 设置绕过免打扰模式
                    channel.setShowBadge(true);
                    // 设置通知圆角围标
                    channel.setBypassDnd(true);
                    // 设置震动
                    channel.enableVibration(true);
                    // 设置声音
//                    channel.setSound();
                    inmanager.createNotificationChannel(channel);
                    Notification notification = new Notification.Builder(NotificationTest.this, id)
                            .setContentTitle("Base Notification View")
                            .setContentText("您有一条新通知")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setStyle(new Notification.MediaStyle())
                            .setAutoCancel(false)
                            .addExtras(new Bundle())
                            //设置意图
                            .setContentIntent(pendingIntent)
                            //圆角图标数字数量
                            .setNumber(10)
                            //设置超时取消通知
//                            .setTimeoutAfter(60 * 1000)
                            //点击后自动取消
                            .setAutoCancel(true)
                            .build();
                    inmanager.notify(1, notification);
                }else{
//                    Toast.makeText(NotificationTest.this,Build.VERSION.SDK,Toast.LENGTH_SHORT).show();
                    Notification notification = new NotificationCompat.Builder(NotificationTest.this,id)
                            .setContentTitle("Base Notification View")
                            .setContentText("您有一条新通知")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            //设置灯的颜色和，亮时长，暗时长
                            .setLights(Color.GREEN,1000,1000)
                            //全部按照默认设置
                            //.setDefaults(NotificationCompat.DEFAULT_ALL)
                            //设置意图
                            .setContentIntent(pendingIntent)
                            //点击后自动取消
                            .setAutoCancel(true)
                            //震动,静，动，静，动
                            .setVibrate(new long[]{0,1000,1000,1000})
                            // 设置声音
//                          .setSound();
                            .build();
                    inmanager.notify(1,notification);
                }
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationTest.this,CameraTest.class);
                startActivity(intent);
            }
        });
    }
}
