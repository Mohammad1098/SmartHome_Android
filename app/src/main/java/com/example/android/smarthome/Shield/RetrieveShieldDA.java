package com.example.android.smarthome.Shield;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import com.example.android.smarthome.DataBase.Schema;

import java.util.ArrayList;

public class RetrieveShieldDA extends AppCompatActivity {



    private Cursor cursor;
    private ArrayList<Shield> shieldArrayList;

    public RetrieveShieldDA(Activity activity , long MicroControllerID){

        String selection = "MICROCONTROLLER_ID="+MicroControllerID;
        cursor = activity.getApplicationContext().getContentResolver().query(Schema.Shield.CONTENT_URI , null , selection , null , null);

    }


    public ArrayList<Shield> retrieveShields(){

        if(cursor == null){

            return null;
        }

        shieldArrayList = new ArrayList<>();

        cursor.moveToFirst();


        for (int i=0 ; i<cursor.getCount() ; i++){

            Shield shield = new Shield();


            shield.setMicroControllerID(cursor.getLong(cursor.getColumnIndex(Schema.Shield.MICROCONTROLLER_ID)));
            shield.setName(cursor.getString(cursor.getColumnIndex(Schema.Shield.NAME)));
            shield.setType(cursor.getInt(cursor.getColumnIndex(Schema.Shield.TYPE)));

            shieldArrayList.add(shield);

            cursor.moveToNext();


        }


        return shieldArrayList;

    }




}
