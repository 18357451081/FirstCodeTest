package com.example.test12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    RecyclerView recyclerView;
    List<Number> numberList = new ArrayList<>();
    private NumberAdpter numberAdpter;
    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        工具栏
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//      滑动刷新
        swipeRefresh = findViewById(R.id.swipe_Refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNumber();
            }
        });
//      悬浮按钮
        FloatingActionButton fab = findViewById(R.id.fab);
//        悬浮按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"clicked to Action",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
//      主页列表
        recyclerView = findViewById(R.id.recyclerView);
        initNumber();
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        numberAdpter = new NumberAdpter(numberList);
        recyclerView.setAdapter(numberAdpter);
//      滑动抽屉
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.home);
        }
        //监听事件
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//      加载菜单文件，放入
        getMenuInflater().inflate(R.menu.toobar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.set:{
                Toast.makeText(this,"clicked to set",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.add:{
                Toast.makeText(this,"clicked to add",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.link:{
                Toast.makeText(this,"clicked to link",Toast.LENGTH_SHORT).show();
                break;
            }
            case android.R.id.home:{
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            }

            default:
                break;

        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_call:
                Toast.makeText(this,"clicked to nav_call",Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_friends:
                Toast.makeText(this,"clicked to nav_friends",Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                break;

            default:
                break;
        }
        return true;
    }
    private void refreshNumber(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15 * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initNumber();
                        numberAdpter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initNumber(){
        for (int i = 0; i < 10; i++) {
            Number one = new Number("One", R.drawable.number1);
            numberList.add(one);
            Number two = new Number("Two", R.drawable.number2);
            numberList.add(two);
            Number three = new Number("Three", R.drawable.number3);
            numberList.add(three);
            Number four = new Number("Four", R.drawable.number4);
            numberList.add(four);
            Number five = new Number("Five", R.drawable.number5);
            numberList.add(five);
            Number six = new Number("Six", R.drawable.number6);
            numberList.add(six);
            Number seven = new Number("Seven", R.drawable.number7);
            numberList.add(seven);
            Number eight = new Number("Eight", R.drawable.number8);
            numberList.add(eight);
            Number nine = new Number("Nine", R.drawable.number9);
            numberList.add(nine);
            Number ten = new Number("Ten", R.drawable.number10);
            numberList.add(ten);
        }
    }
}

