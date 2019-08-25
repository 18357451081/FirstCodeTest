package com.example.test7.Chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test7.R;

public class ProviderTest extends AppCompatActivity {
    Button query,updata,delete,add;
    EditText text;
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);
        query = findViewById(R.id.queryData);
        updata = findViewById(R.id.upData);
        delete = findViewById(R.id.deleteData);
        add = findViewById(R.id.addData);

        text = findViewById(R.id.editText);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.ccd.administrator.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages","1111");
                values.put("price","98.3");
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.ccd.administrator.provider/book");
                Cursor cursor =getContentResolver().query(uri,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name")) + "  ";
                        String auther = cursor.getString(cursor.getColumnIndex("author")) + "  ";
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        text.append(name+auther+pages+price+"null");
                    }
                    cursor.close();
                }
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.ccd.administrator.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("price",18.3);//修改的数据
                values.put("pages",3);
                getContentResolver().update(uri,values,null,null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.ccd.administrator.provider/book/" + newId);
                getContentResolver().delete(uri,null,null);
            }
        });

    }
}
