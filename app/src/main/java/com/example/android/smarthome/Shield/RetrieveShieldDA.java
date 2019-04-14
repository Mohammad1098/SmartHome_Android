package com.example.android.smarthome.Shield;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.MicroController.MicroController;
import com.example.android.smarthome.Pins.RetrieveListOfPinsController;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class RetrieveShieldDA extends AppCompatActivity {


    private Cursor cursor;
    private ArrayList<Shield> shieldArrayList;
    private long MicroControllerID;
    private Activity activity;


    public RetrieveShieldDA(Activity activity, long MicroControllerID) {

        this.activity = activity;
        this.MicroControllerID = MicroControllerID;


    }


    public ArrayList<Shield> retrieveShields() {

        String selection = "MICROCONTROLLER_ID=" + this.MicroControllerID;
        Cursor cursor = this.activity.getApplicationContext().getContentResolver().query(Schema.Shield.CONTENT_URI, null, selection, null, null);

        if (cursor == null) {

            return null;
        }

        shieldArrayList = new ArrayList<>();

        cursor.moveToFirst();


        for (int i = 0; i < cursor.getCount(); i++) {

            Shield shield = new Shield();


            shield.setMicroControllerID(cursor.getLong(cursor.getColumnIndex(Schema.Shield.MICROCONTROLLER_ID)));
            shield.setName(cursor.getString(cursor.getColumnIndex(Schema.Shield.NAME)));
            shield.setType(cursor.getInt(cursor.getColumnIndex(Schema.Shield.TYPE)));


            shieldArrayList.add(shield);

            cursor.moveToNext();


        }


        return shieldArrayList;

    }


    public ArrayList<ShieldCategory> returnRelayListSpinner() {

        String selection = "MICROCONTROLLER_ID=" + this.MicroControllerID + " AND TYPE=1"; //TYPE 1 for relay , 2 for IR
        Cursor cursor = this.activity.getApplicationContext().getContentResolver().query(Schema.Shield.CONTENT_URI, null, selection, null, null);


        if (cursor == null) {

            return null;
        }


        ArrayList<ShieldCategory> RelaySpinnerArrayList = new ArrayList<>();

        cursor.moveToFirst();


        for (int i = 0; i < cursor.getCount(); i++) {


            if (cursor.getInt(cursor.getColumnIndex(Schema.Shield.AVAILABILITY)) == 0) {  // AVAILABILITY ==0 we want relay that not been used by other devices

                ShieldCategory shieldCategory = new ShieldCategory();
                shieldCategory.setShieldName(cursor.getString(cursor.getColumnIndex(Schema.Shield.NAME)));
                shieldCategory.setShieldImage(R.drawable.ic_relay);
                shieldCategory.setType(1);
                shieldCategory.setPin(returnShieldPin(cursor.getLong(cursor.getColumnIndex(Schema.Shield.ID))));

                shieldCategory.setShieldName(cursor.getString(cursor.getColumnIndex(Schema.Shield.NAME)));
                shieldCategory.setShieldImage(R.drawable.relay);
                shieldCategory.setType(1);
                shieldCategory.setPin(returnShieldPin(cursor.getLong(cursor.getColumnIndex(Schema.Shield.ID))));
                shieldCategory.setShieldID(cursor.getInt(cursor.getColumnIndex(Schema.Shield.ID)));
                RelaySpinnerArrayList.add(shieldCategory);

            }
            cursor.moveToNext();


        }


        return RelaySpinnerArrayList;


    }


    public ArrayList<ShieldCategory> returnIRListListSpinner() {

        String selection = "MICROCONTROLLER_ID=" + this.MicroControllerID + " AND TYPE=2"; // TYPE 1 for relay , 2 for IR
        Cursor cursor = this.activity.getApplicationContext().getContentResolver().query(Schema.Shield.CONTENT_URI, null, selection, null, null);


        if (cursor == null) {

            return null;
        }

        ArrayList<ShieldCategory> IRSpinnerArrayList = new ArrayList<>();

        cursor.moveToFirst();


        for (int i = 0; i < cursor.getCount(); i++) {

            ShieldCategory shieldCategory = new ShieldCategory();


            shieldCategory.setShieldName(cursor.getString(cursor.getColumnIndex(Schema.Shield.NAME)));
            shieldCategory.setShieldImage(R.drawable.ic_ir);
            shieldCategory.setType(2);
            shieldCategory.setPin(returnShieldPin(cursor.getLong(cursor.getColumnIndex(Schema.Shield.ID))));
            shieldCategory.setShieldID(cursor.getInt(cursor.getColumnIndex(Schema.Shield.ID)));

            IRSpinnerArrayList.add(shieldCategory);

            cursor.moveToNext();


        }


        return IRSpinnerArrayList;


    }


    private int returnShieldPin(long ShieldID) {


        RetrieveListOfPinsController retrieveListOfPinsController = new RetrieveListOfPinsController(this.activity);

        return retrieveListOfPinsController.returnShieldPin(MicroControllerID, ShieldID);

    }


}
