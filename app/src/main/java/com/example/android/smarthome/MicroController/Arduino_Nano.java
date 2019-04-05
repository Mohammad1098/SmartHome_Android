package com.example.android.smarthome.MicroController;

import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

public class Arduino_Nano extends MicroController {



    public Arduino_Nano(long microControllerId){


        this.setLong_id(microControllerId);

    }

    @Override
    public ArrayList<ContentValues> returnPinTableContentValues() {

        ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();
        ContentValues contentValuesPinTableDigital;
        ContentValues contentValuesPinTableAnalog ;


        Log.e("Nano ", "Micro ID"+this.getLong_id());

        for (int i=0 ; i <13 ; i++) {

            contentValuesPinTableDigital = new ContentValues();


            contentValuesPinTableDigital.put("PINNUMBER", i);
            contentValuesPinTableDigital.put("MICROCONTROLLERID", this.getLong_id());
            // 1 means it's available
            contentValuesPinTableDigital.put("AVAILABILITY", 1);
            // 0 means it's Digital
            contentValuesPinTableDigital.put("TYPE", 0);

            contentValuesArrayList.add(contentValuesPinTableDigital);

        }


        for (int i=0 ; i <8 ; i++) {

            contentValuesPinTableAnalog = new ContentValues();


            contentValuesPinTableAnalog.put("PINNUMBER", i);
            contentValuesPinTableAnalog.put("MICROCONTROLLERID", this.getLong_id());
            // 1 means it's available
            contentValuesPinTableAnalog.put("AVAILABILITY", 1);
            // 1 means it's Analog
            contentValuesPinTableAnalog.put("TYPE", 1);

            contentValuesArrayList.add(contentValuesPinTableAnalog);

        }

        return contentValuesArrayList;

    }
}
