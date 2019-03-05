package com.example.android.smarthome.MicroController;

import android.app.Activity;
import android.database.Cursor;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;

import java.util.ArrayList;

public class RetrieveListOfMicroControllerDA {



    private ArrayList<MicroController> microControllerArrayList;
    private Activity activity;
    private Cursor cursor;

    public RetrieveListOfMicroControllerDA(Activity activity ){

        this.activity = activity;
        this.activity.getContentResolver();

        cursor = this.activity.getContentResolver().query(Schema.MicroController.CONTENT_URI , null , null , null , null);
    }



    public ArrayList<MicroController> retrieveMicroControllersDA(){


        if(cursor == null){

            return null;
        }

        microControllerArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){

            MicroController microController = new MicroController();

            //manipulate data
            microController.setName(cursor.getString(cursor.getColumnIndex(Schema.MicroController.NAME)));
            microController.setImage(cursor.getInt(cursor.getColumnIndex(Schema.MicroController.ROOM)));
            microController.setLong_id(cursor.getLong(cursor.getColumnIndex(Schema.MicroController.ID)));



            // add device to the list
            microControllerArrayList.add(microController);


            // move to next position
            cursor.moveToNext();


        }



        return microControllerArrayList;

    }

}
