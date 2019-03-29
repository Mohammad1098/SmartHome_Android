package com.example.android.smarthome.MicroController;

import android.content.ContentValues;

import java.util.ArrayList;

public abstract class MicroController {


    private String name,roomName;
    private int image;
    private long long_id;


    public MicroController(String name , int image , long long_id){

        this.name = name;
        this.image = image;
        this.long_id = long_id;


    }

    public MicroController(){}


    public String getRoomName() {return roomName;}

    public void setRoomName(String roomName) { this.roomName = roomName;}

    public void setName(String name) {this.name = name;}

    public void setImage(int image) {
        this.image = image;
    }

    public void setLong_id(long long_id) {this.long_id = long_id;}

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public long getLong_id() {return long_id;}


    public abstract ArrayList<ContentValues> returnPinTableContentValues();

}
