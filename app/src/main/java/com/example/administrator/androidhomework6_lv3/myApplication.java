package com.example.administrator.androidhomework6_lv3;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/20.
 */

public class myApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }
     public static Context getContext(){
        return context;
     }
}
