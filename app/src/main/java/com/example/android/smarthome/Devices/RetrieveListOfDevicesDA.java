package com.example.android.smarthome.Devices;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import java.util.ArrayList;
import android.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.smarthome.DataBase.Schema;

public class RetrieveListOfDevicesDA extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private Cursor cursor;


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // return all data in device table
        return new CursorLoader(this, Schema.Device.CONTENT_URI , null , null , null , null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        cursor=data;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public ArrayList<Device> retrieveDevices(){
        // in this method we will create a list of devices , manipulate the cursor to get the data and add it to list of devices

        if(cursor == null){

            return null;
        }

        ArrayList<Device> arrayList = new ArrayList<>();

        // by default cursor will point to -1 position
        cursor.moveToFirst();

        for (int i = 0 ; i < cursor.getCount() ; i++){

            Device device = new Device();

            //manipulate data
            device.setName(cursor.getString(cursor.getColumnIndex(Schema.Device.NAME)));
            device.setType(cursor.getInt(cursor.getColumnIndex(Schema.Device.TYPE)));
            device.setRoom(cursor.getInt(cursor.getColumnIndex(Schema.Device.ROOM)));

            // add device to the list
            arrayList.add(device);


            // move to next position
            cursor.moveToNext();


        }


        return arrayList;

    }

}
