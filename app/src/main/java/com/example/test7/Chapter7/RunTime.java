package com.example.test7.Chapter7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test7.MainActivity;
import com.example.test7.R;

public class RunTime extends AppCompatActivity {
    Button callPhone,contactView,providerTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time);
        callPhone = findViewById(R.id.callPhone);
        contactView = findViewById(R.id.contactView);
        providerTest = findViewById(R.id.providerTest);
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查自己的具体权限与包里的权限是否相等
                if(ContextCompat.checkSelfPermission(RunTime.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    //申请权限，，请求码
                    ActivityCompat.requestPermissions(RunTime.this,new String[]{Manifest.permission.CALL_PHONE},1);

                }else{
                    call();
                }
            }
        });
        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RunTime.this, ContackList.class);
                startActivity(intent);
            }
        });
        providerTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RunTime.this, ProviderTest.class);
                startActivity(intent);
            }
        });
    }
    private void call(){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
    //调用requestPermissions后，系统弹出申请对话框，用户选择同意或拒绝都会回调onRequestPermissionsResult之中
    //授权结果会封装在grantResults参数之中
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(this,"You denied the Permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
