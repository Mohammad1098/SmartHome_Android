package com.example.android.smarthome.MicroController;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;

public class RetrieveSpecificMicroControllerDA {

    private Activity activity;
    private Cursor cursor;

    public RetrieveSpecificMicroControllerDA(Activity activity  , long MicroControllerId){

        this.activity = activity;
        String selection = "MICROCONTROLLERID="+MicroControllerId;
        cursor = this.activity.getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);

    }


    public void TEST_displaySpecificMicroControllerTable(){

        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){



            Log.e("Specific Micro DA" , "Pin Number "+String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)))+"\n");
            //Log.e("Specific Micro DA" , "Device Id "+String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.DEVICE_ID)))+"\n");
            Log.e("Specific Micro DA" , "Avail " +String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.AVAILABILITY)))+"\n");
            Log.e("Specific Micro DA" , "Type " +String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.TYPE)))+"\n");
            Log.e("Specific Micro DA", "////////////////////");




            // move to next position
            cursor.moveToNext();


        }

    }

}
