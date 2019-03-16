package com.example.android.smarthome.Pins;

import android.app.Activity;
import android.database.Cursor;

import com.example.android.smarthome.DataBase.Schema;

import java.util.ArrayList;

public class RetrieveListOfPinsDA {

    private Cursor cursor;
    private Activity activity;

    public RetrieveListOfPinsDA(Activity activity , long MicroController_ID){

        this.activity = activity;

        String selection = "MICROCONTROLLERID="+MicroController_ID;
        cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);


    }

    public boolean checkAvailabilityOfPins(ArrayList<Integer> pins){

        if(cursor == null){

            return false;
        }



        for (int i=0 ; i<pins.size() ; i++){

            if(pins.get(i) == 1)

        }


    }


    private boolean checkIndividualPin(Integer pin){


        cursor.moveToFirst();


        for (int i=0 ; i<cursor.getCount() ; i++){


                if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)) == pin){

                    if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.AVAILABILITY)) == 0){

                        return false;
                    }

                }


                cursor.moveToNext();


        }

        return true;

    }
}
