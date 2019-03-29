package com.example.android.smarthome.MicroController;

import android.content.ContentValues;

import java.util.ArrayList;

public class Arduino_Uno_R3 extends MicroController{


    public Arduino_Uno_R3(long microControllerId){


        this.setLong_id(microControllerId);

    }


    @Override
    public ArrayList<ContentValues> returnPinTableContentValues() {

        ArrayList<ContentValues> contentValuesArrayList = new ArrayList<>();
        ContentValues contentValuesPinTableDigital = new ContentValues();
        ContentValues contentValuesPinTableAnalog = new ContentValues();


        for (int i=0 ; i <14 ; i++) {

            contentValuesPinTableDigital.put("PINNUMBER", i);
            contentValuesPinTableDigital.put("MICROCONTROLLERID", this.getLong_id());
            // 1 means it's available
            contentValuesPinTableDigital.put("AVAILABILITY", 1);
            // 0 means it's Digital
            contentValuesPinTableDigital.put("TYPE", 0);

            contentValuesArrayList.add(contentValuesPinTableDigital);
        }


        for (int i=0 ; i <6 ; i++) {

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
