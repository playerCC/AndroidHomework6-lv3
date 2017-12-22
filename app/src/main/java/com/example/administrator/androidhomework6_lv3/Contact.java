package com.example.administrator.androidhomework6_lv3;

/**
 * Created by Administrator on 2017/12/20.
 */

public class Contact {
    private String name;
    private int imageId;
    public Contact(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }

}
