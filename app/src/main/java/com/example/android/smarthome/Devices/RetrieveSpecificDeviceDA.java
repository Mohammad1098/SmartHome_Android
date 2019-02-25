package com.example.android.smarthome.Devices;


import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import java.util.ArrayList;


public class RetrieveSpecificDeviceDA {


    private ArrayList<Device> speciificDeviceArrayList;
    private Activity activity;
    private Cursor cursor;


    public RetrieveSpecificDeviceDA(Activity activity , int type) {

        this.activity = activity;

        String selection = "TYPE="+type;
        cursor = activity.getApplicationContext().getContentResolver().query(Schema.Device.CONTENT_URI, null, selection, null, null);


    }


    public ArrayList<Device> retrieveSpecificDevicesDA() {

        // in this method we will create a list of devices , manipulate the cursor to get the data and add it to list of devices

        if (cursor == null) {


            return null;
        }

        speciificDeviceArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            Device device = new Device();

            //manipulate data
            device.setName(cursor.getString(cursor.getColumnIndex(Schema.Device.NAME)));
            device.setType(cursor.getInt(cursor.getColumnIndex(Schema.Device.TYPE)));
            device.setRoom(cursor.getString(cursor.getColumnIndex(Schema.Device.ROOM)));
            device.setId(cursor.getInt(cursor.getColumnIndex(Schema.Device.ID)));


            // add device to the list
            speciificDeviceArrayList.add(device);


            // move to next position
            cursor.moveToNext();


        }

        return speciificDeviceArrayList;

    }


}
