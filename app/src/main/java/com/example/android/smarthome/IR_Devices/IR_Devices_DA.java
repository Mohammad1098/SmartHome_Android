package com.example.android.smarthome.IR_Devices;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Operation.Operation;

import java.util.ArrayList;

public class IR_Devices_DA {



    private ArrayList<Operation> operationArrayList;
    private Activity activity;


    public IR_Devices_DA(Activity activity ){

        this.activity = activity;


    }


    public ArrayList<Operation> retrieveOperation(long MicroControllerId , long deviceId ){



        String selection = "DEVICEID="+deviceId;
        Cursor cursor = this.activity.getContentResolver().query(Schema.Operation.CONTENT_URI , null , selection , null , null);


        if(cursor == null){

            return null;
        }


        Log.e("IR DA" , "CURSOR "+cursor.getCount());

        cursor.moveToFirst();


        operationArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){


            Operation operation = new Operation();

            operation.setName(cursor.getString(cursor.getColumnIndex(Schema.Operation.NAME)));


            operationArrayList.add(operation);


            // move to next position
            cursor.moveToNext();


        }



        return operationArrayList;

    }


}
