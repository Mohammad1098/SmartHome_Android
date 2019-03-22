package com.example.android.smarthome.Pins;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

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

        boolean availability;


        for (int i=0 ; i<pins.size() ; i++){

            if(!checkArduinoPins(pins.get(i))){

                return false;
            }

            availability =checkIndividualPin(pins.get(i));

            if(availability ==false){

                return false;
            }

        }

        return true;


    }


    private boolean checkIndividualPin(Integer pin){

        if(cursor == null){

            return false;
        }

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


    private boolean checkArduinoPins(int pin ){

          //arduino uno has 0-13 pin

                if(pin >13 || pin <0){

                    return false;
                }

                return true;
    }
}
