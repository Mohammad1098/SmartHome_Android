package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.database.Cursor;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Shield.Shield;


public class RetrieveListOfDevicesDA extends AppCompatActivity  {


    private Activity activity;
    public RetrieveListOfDevicesDA(Activity activity ){

        this.activity =activity;

    }



    public ArrayList<Device> retrieveDevicesDA(){

        ArrayList<Device> devicesarrayList;

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Device.CONTENT_URI, null, null, null, null);


        if (cursor == null) {


            return null;
        }

        devicesarrayList = new ArrayList<>();

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
            devicesarrayList.add(device);


            // move to next position
            cursor.moveToNext();


        }


        return devicesarrayList;

    }


    public void TEST_displayDeviceTable(){


        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Device.CONTENT_URI, null, null, null, null);

        cursor.moveToFirst();


        for (int i=0 ; i<cursor.getCount() ; i++){


            Log.e("Device DA" , "ID "+String.valueOf(cursor.getLong(cursor.getColumnIndex(Schema.Device.ID))));
            Log.e("Device DA" , "NAME "+cursor.getString(cursor.getColumnIndex(Schema.Device.NAME)));
            Log.e("Device DA" , "TYPE  "+String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Device.TYPE))));
            Log.e("Device DA" , "Room "+cursor.getString(cursor.getColumnIndex(Schema.Device.ROOM)));
            Log.e("Device DA" , "MicroController ID "+String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Device.MICROCONTROLLER_ID))));


            cursor.moveToNext();


        }

    }


}
