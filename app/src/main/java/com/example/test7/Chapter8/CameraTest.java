package com.example.test7.Chapter8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.test7.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class CameraTest extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    Button takePhoto,getPermission,choosePhoto;
    ImageView picture;
    private Uri imageUri;
    private File cameraSavePath;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    private String[] permissions = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);
        takePhoto = findViewById(R.id.takePhoto);
        getPermission = findViewById(R.id.getPermission);
        choosePhoto = findViewById(R.id.getPhoto);
        picture = findViewById(R.id.pictureView);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                goCamera();
            }
        });
        getPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermission();
            }
        });
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });
    }
    //回调结果
    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            String photoPath;
            switch (requestCode){
                case TAKE_PHOTO:{
                    if (resultCode == RESULT_OK) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            photoPath = String.valueOf(cameraSavePath);
                        } else {
                            photoPath = imageUri.getEncodedPath();
                        }
                        Log.d("拍照返回图片路径:", photoPath);
                        Glide.with(CameraTest.this).load(photoPath).into(picture);
                    }
                    break;
                }
                case CHOOSE_PHOTO:{
                    photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
                    Glide.with(CameraTest.this).load(photoPath).into(picture);
                }
                default:
                    break;
            }
            super.onActivityResult(requestCode,resultCode,data);
    }
    //激活相机操作
    private void goCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            imageUri = FileProvider.getUriForFile(CameraTest.this,"com.example.test7.fileProvider",cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else{
            imageUri = Uri.fromFile(cameraSavePath);
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        CameraTest.this.startActivityForResult(intent,TAKE_PHOTO);
    }
    //激活相册操作
    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    //获取权限
    private void getPermission(){
        if(EasyPermissions.hasPermissions(this,permissions)){
            Toast.makeText(this,"已申请相关权限",Toast.LENGTH_SHORT).show();
        }else{
            EasyPermissions.requestPermissions(this,"需要获取您的相机、相册适用权限",1,permissions);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"权限申请成功的回调",Toast.LENGTH_SHORT).show();
    }
    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"权限申请拒绝的回调",Toast.LENGTH_SHORT).show();
    }
}
