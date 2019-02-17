package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.database.Cursor;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;


public class RetrieveListOfDevicesDA extends AppCompatActivity  {


    private ArrayList<DeviceCategory> devicesarrayList;
    private Activity activity;
    private Cursor cursor;

    public RetrieveListOfDevicesDA(Activity activity ){
        this.activity = activity;
        cursor = activity.getApplicationContext().getContentResolver().query(Schema.DeviceCategory.CONTENT_URI , null , null , null , null);

    }



    public ArrayList<DeviceCategory> retrieveDevicesDA(){

        // in this method we will create a list of devices , manipulate the cursor to get the data and add it to list of devices

        if(cursor == null){

            return null;
        }

        devicesarrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){

            DeviceCategory deviceCategory = new DeviceCategory();

            //manipulate data
            deviceCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(Schema.DeviceCategory.NAME)));
            deviceCategory.setType(cursor.getInt(cursor.getColumnIndex(Schema.DeviceCategory.TYPE)));
            deviceCategory.setId(cursor.getInt(cursor.getColumnIndex(Schema.DeviceCategory.ID)));


            // add device to the list
            devicesarrayList.add(deviceCategory);


            // move to next position
            cursor.moveToNext();


        }



        return devicesarrayList;

    }



}
