package com.example.android.smarthome.Operation;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;

import java.util.ArrayList;

public class RetrieveListOfOperationDA extends AppCompatActivity {



    private ArrayList<Operation> operationsArrayList;
    private Activity activity;
    private Cursor cursor;

    public RetrieveListOfOperationDA(Activity activity , long id){

        this.activity = activity;

        Log.e("OperationDA" ,String.valueOf(id) );


        String selection = "DEVICEID="+id;


        cursor = activity.getApplicationContext().getContentResolver().query(Schema.Operation.CONTENT_URI , null , selection , null , null);

    }





    public ArrayList<Operation> retrieveOperations(){

        // in this method we will create a list of devices , manipulate the cursor to get the data and add it to list of devices

        if(cursor == null){

            return null;
        }

        operationsArrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){

            Operation operation = new Operation();

            //manipulate data
            operation.setName(cursor.getString(cursor.getColumnIndex(Schema.Operation.NAME)));
            operation.setImplementation(cursor.getString(cursor.getColumnIndex(Schema.Operation.IMPLEMENTATION)));


            // add device to the list
            operationsArrayList.add(operation);


            // move to next position
            cursor.moveToNext();


        }


        return operationsArrayList;

    }


}
