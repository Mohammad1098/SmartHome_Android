package com.example.android.smarthome.Lights;

import android.app.Activity;
import android.content.ContentValues;

import com.example.android.smarthome.DataBase.Schema;

public class LightBulbDA {

    private Activity activity;

    public LightBulbDA(Activity activity){

        this.activity = activity;

    }


    public boolean setTimeToDA(long deviceId , int hours , int minutes){

        int time = hours + minutes;

        ContentValues contentValues =  new ContentValues();


        contentValues.put(Schema.LightBulb.DEVICE_ID , deviceId);
        contentValues.put(Schema.LightBulb.TIME , time); // 0 ---->Light Bulb     1 ----> Rgb led strip       2 ----> TV      3 ----> Receiver      4 ----> AC      5 ----> OTHER


        this.activity.getContentResolver().insert(Schema.LightBulb.CONTENT_URI, contentValues);



        return  true;
    }
}
