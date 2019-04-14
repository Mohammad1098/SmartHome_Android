package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.MicroController.MicroController;

import java.util.ArrayList;

public class RetrieveDevicesDA {


    private ArrayList<Device> devicesArrayList;
    private Activity activity;


    public RetrieveDevicesDA(Activity activity ){

        this.activity = activity;


    }


    public ArrayList<Device> retrieveDevices(long MicroControllerId , int deviceType ){

        if (deviceType == 100){ // devicetype ---> 100 means return all devices of microcontroller

            String selection = "MICROCONTROLLER_ID="+MicroControllerId;
            Cursor cursor = this.activity.getContentResolver().query(Schema.Device.CONTENT_URI , null , selection , null , null);

        }


        String selection = "MICROCONTROLLER_ID="+MicroControllerId +" AND TYPE="+deviceType;
        Cursor cursor = this.activity.getContentResolver().query(Schema.Device.CONTENT_URI , null , selection , null , null);


        if(cursor == null){

            return null;
        }

        devicesArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){


            Device device = new Device();

            device.setId(cursor.getInt(cursor.getColumnIndex(Schema.Device.ID)));
            device.setMicroController_Id(cursor.getInt(cursor.getColumnIndex(Schema.Device.MICROCONTROLLER_ID)));
            device.setType(deviceType);
            device.setName(cursor.getString(cursor.getColumnIndex(Schema.Device.NAME)));
            device.setRoom(cursor.getString(cursor.getColumnIndex(Schema.Device.ROOM)));


            devicesArrayList.add(device);


            // move to next position
            cursor.moveToNext();


        }



        return devicesArrayList;

    }


}
