package com.example.administrator.androidhomework6_lv3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/20.
 */

public class Information extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    ImageView imageView;
    TextView textView_name;
    EditText editText_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
        editText_phone = findViewById(R.id.txt_phone);
        imageView = findViewById(R.id.pic);
        textView_name = findViewById(R.id.txt_UserName);
        Intent intent1 = getIntent();
        final String name = intent1.getStringExtra("name");

        dbHelper = new MyDatabaseHelper(Information.this,"Contact.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Contact",new String[]{"imageId,name,phoneNum"},"name = ?",new String[]{name},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            do{
                int dbimageId = cursor.getInt(cursor.getColumnIndex("imageId"));
                String dbname = cursor.getString(cursor.getColumnIndex("name"));
                String dbphoneNum = cursor.getString(cursor.getColumnIndex("phoneNum"));
                imageView.setImageResource(dbimageId);
                textView_name.setText(dbname);
                editText_phone.setText(dbphoneNum);
            }while(cursor.moveToNext());
            cursor.close();
            db.close();
        }
        Button button1 = findViewById(R.id.call);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = editText_phone.getText().toString();
                if(!phoneNum.isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel" + phoneNum));
                    startActivity(intent);
                }else{
                    Toast.makeText(v.getContext(),"号码为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button = findViewById(R.id.backTo_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper = new MyDatabaseHelper(Information.this,"Contact.db",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", textView_name.getText().toString());
                values.put("phoneNum",editText_phone.getText().toString());
                db.update("Contact",values,"name = ?",new String[]{name});
                values.clear();

                Toast.makeText(v.getContext(), "保存成功!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Information.this, MainActivity.class);
                startActivity(intent);
            }
        });
        textView_name.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    textView_name.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
    }
}
