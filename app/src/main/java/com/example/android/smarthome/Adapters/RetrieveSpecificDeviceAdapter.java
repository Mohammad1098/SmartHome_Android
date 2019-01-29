package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.smarthome.R;


// this class is used by any category of devices such as LightBulb , Tv , RGB LightBulb etc to present the list of the devices


public class RetrieveSpecificDeviceAdapter extends CursorAdapter {

    private String deviceType;

    public RetrieveSpecificDeviceAdapter(Context context , Cursor cursor ,String deviceType){


        super(context , cursor , false);
        this.deviceType=deviceType;

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // if the list item is empty we create a new item and we choose what layout we want , pass the parent which is the list item  , and pass false
        return LayoutInflater.from(context).inflate(R.layout.light_bulb_list_item , parent , false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        //here we will format the list item by retrieving data from cursor we need name , room , and status

        TextView deviceName = view.findViewById(R.id.device_name_Lay_specific_device_list_item);

        TextView deviceRoom = view.findViewById(R.id.device_room_Lay_specific_device_list_item);

        TextView deviceStatus = view.findViewById(R.id.device_status_Lay_specific_device_list_item);


        deviceName.setText(cursor.getString(cursor.getColumnIndex("Schema."+deviceType+".NAME")));

        deviceRoom.setText(cursor.getString(cursor.getColumnIndex("Schema."+deviceType+".ROOM")));

        deviceStatus.setText(cursor.getString(cursor.getColumnIndex("Schema."+deviceType+".STATUS")));

    }


}
