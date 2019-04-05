package com.example.android.smarthome.Devices;


import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import java.util.ArrayList;


public class RetrieveMicroControllerDevicesDA {


    private ArrayList<Device> MicroControllerDevicesArrayList;
    private Activity activity;
    private Cursor cursor;

    public RetrieveMicroControllerDevicesDA(Activity activity ) {

        this.activity = activity;

    }


    public ArrayList<Device> retrieveMicroControllerDevicesByMicroController( long MicroController) {

        String selection = "MICROCONTROLLER_ID="+MicroController;
        cursor = activity.getApplicationContext().getContentResolver().query(Schema.Device.CONTENT_URI, null, selection, null, null);


        if (cursor == null) {


            return null;
        }

        MicroControllerDevicesArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            Device device = new Device();

            //manipulate data
            device.setName(cursor.getString(cursor.getColumnIndex(Schema.Device.NAME)));
            device.setType(cursor.getInt(cursor.getColumnIndex(Schema.Device.TYPE)));
            device.setRoom(cursor.getString(cursor.getColumnIndex(Schema.Device.ROOM)));
            device.setId(cursor.getInt(cursor.getColumnIndex(Schema.Device.ID)));
            device.setMicroController_Id(cursor.getInt(cursor.getColumnIndex(Schema.Device.MICROCONTROLLER_ID)));

            // add device to the list
            MicroControllerDevicesArrayList.add(device);


            // move to next position
            cursor.moveToNext();


        }

        return MicroControllerDevicesArrayList;

    }

}
