package com.example.android.smarthome.MicroController;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;

import java.util.ArrayList;

public class RetrieveListOfMicroControllerDA {



    private ArrayList<MicroController> microControllerArrayList;
    private Activity activity;
    private Cursor cursor;

    public RetrieveListOfMicroControllerDA(Activity activity ){

        this.activity = activity;

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

            MicroController microController = new MicroController() {
                @Override
                public ArrayList<ContentValues> returnPinTableContentValues() {
                    return null;
                }
            };

            //manipulate data
            microController.setName(cursor.getString(cursor.getColumnIndex(Schema.MicroController.NAME)));
            microController.setRoomName(cursor.getString(cursor.getColumnIndex(Schema.MicroController.ROOM)));
            microController.setLong_id(cursor.getLong(cursor.getColumnIndex(Schema.MicroController.ID)));


            /*
            Log.e("Micro DA" , "ID "+String.valueOf(cursor.getLong(cursor.getColumnIndex(Schema.MicroController.ID))));
            Log.e("Micro DA" , "Name "+cursor.getString(cursor.getColumnIndex(Schema.MicroController.NAME)));
            Log.e("Micro DA" , "Room " +cursor.getString(cursor.getColumnIndex(Schema.MicroController.ROOM)));
        */

            // add device to the list
            microControllerArrayList.add(microController);


            // move to next position
            cursor.moveToNext();


        }



        return microControllerArrayList;

    }


    public int returnMicroControllerType (long MicroControllerID){


        if(cursor == null){

            return -1;
        }

        microControllerArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){

            if(cursor.getLong(cursor.getColumnIndex(Schema.MicroController.ID)) == MicroControllerID){

                return cursor.getInt(cursor.getColumnIndex(Schema.MicroController.TYPE));

            }


            // move to next position
            cursor.moveToNext();


        }
        return -1;

    }

    public void TEST_displayMicroControllerTable(){

        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){



            Log.e("Micro DA" , "ID "+String.valueOf(cursor.getLong(cursor.getColumnIndex(Schema.MicroController.ID))));
            Log.e("Micro DA" , "Name "+cursor.getString(cursor.getColumnIndex(Schema.MicroController.NAME)));
            Log.e("Micro DA" , "Room " +cursor.getString(cursor.getColumnIndex(Schema.MicroController.ROOM)));



            // move to next position
            cursor.moveToNext();


        }

    }

}
