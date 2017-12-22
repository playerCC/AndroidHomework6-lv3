package com.example.administrator.androidhomework6_lv3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MainActivity extends AppCompatActivity{
    private List<Contact> contactList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContact();

        dbHelper = new MyDatabaseHelper(this,"Contact.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor cursor = db.query("Contact",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Contact contact = new Contact(name,imageId);
                contactList.add(contact);
            }while (cursor.moveToNext());
        }cursor.close();
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final mAdapter adapter = new mAdapter(myApplication.getContext(),contactList);
        recyclerView.setAdapter(adapter);
        Button button_add = findViewById(R.id.button_add1);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(MainActivity.this);
            }
        });
            Button button_delete = findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2(MainActivity.this);
            }
        });
    }
    public void initContact(){
            dbHelper = new MyDatabaseHelper(this,"Contact.db",null,1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Contact",null,null,null,null,null,null);
            if(cursor.getCount()==0){
                for(int i=0; i<2; i++){
                    ContentValues values = new ContentValues();
                    values.put("imageId", R.mipmap.a1);
                    values.put("name", "凯隐");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a2);
                    values.put("name", "洛");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a3);
                    values.put("name", "霞");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a4);
                    values.put("name", "卡蜜尔");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a5);
                    values.put("name", "克烈");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a6);
                    values.put("name", "艾翁");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a7);
                    values.put("name", "塔莉垭");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a8);
                    values.put("name", "索尔");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a9);
                    values.put("name", "烬");
                    db.insert("Contact",null,values);
                    values.clear();
                    values.put("imageId", R.mipmap.a10);
                    values.put("name", "俄洛伊");
                    db.insert("Contact",null,values);
                    values.clear();
                }
            }

    }
    public void showDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogView = inflater.inflate(R.layout.dialog_layout,null);
        final AlertDialog alertDialog = builder.setView(dialogView).create();
        final EditText editText = dialogView.findViewById(R.id.name_edi);     //EditText空指针崩溃,find之前加上View实例对象
        Button loginBtn1 = dialogView.findViewById(R.id.button_return);
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Button loginBtn = dialogView.findViewById(R.id.button_add);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDatabaseHelper(activity,"Contact.db",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", editText.getText().toString());
                values.put("imageId",R.drawable.index);
                db.insert("Contact",null,values);
                values.clear();
                alertDialog.dismiss();
                reStartActivity();
            }
        });
        alertDialog.show();
    }

    public void showDialog2(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogView = inflater.inflate(R.layout.dialog_layout2,null);
        final AlertDialog alertDialog = builder.setView(dialogView).create();
        Button loginBtn1 = dialogView.findViewById(R.id.button_return);
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Button loginBtn = dialogView.findViewById(R.id.button_add);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDatabaseHelper(activity,"Contact.db",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Contact",null,null);
                reStartActivity();
            }
        });
        alertDialog.show();
    }

    private void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
