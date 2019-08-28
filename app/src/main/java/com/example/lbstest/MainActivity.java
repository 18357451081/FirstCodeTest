package com.example.lbstest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建位置客户端
        mLocationClient = new LocationClient(getApplicationContext());
        //注册一个监听器，获取位置后，回调这个监听器
        mLocationClient.registerLocationListener(new MyLocationListener());

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.bdMipView);
        positionText = findViewById(R.id.position_TextView);
        //获取地图
        baiduMap = mapView.getMap();
        //设置我的位置
        baiduMap.setMyLocationEnabled(true);
        getPermission();
        initLocation();
        mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update;
            update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        //将当前的经纬 传给Builder ，显示在地图上
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }
    private void initLocation(){
        //位置客户端的选项
        LocationClientOption option = new LocationClientOption();
        //设置刷新间隔
        option.setScanSpan(1 * 1000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }
    private void getPermission(){
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        if(EasyPermissions.hasPermissions(this,permissions)){

        }else {
            EasyPermissions.requestPermissions(this,"申请权限",1,permissions);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation
                    || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
//            StringBuilder currentPosition = new StringBuilder();
//            currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
//            currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
//            currentPosition.append("速度：").append(bdLocation.getSpeed()).append(" m/s\n");
//            currentPosition.append("半径：").append(bdLocation.getRadius()).append("\n");
//            currentPosition.append("时间：").append(bdLocation.getTime()).append("\n");
//            currentPosition.append("定位方式：");
//            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
//                currentPosition.append("GPS");
//            }else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
//                currentPosition.append("网络");
//            }
//            positionText.setText(currentPosition);
        }
    }
}
