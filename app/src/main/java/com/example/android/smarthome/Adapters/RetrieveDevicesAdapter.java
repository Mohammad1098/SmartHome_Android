package com.example.android.smarthome.Adapters;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.example.android.smarthome.R;

// this class will use to list the all Device in the system
public class RetrieveDevicesAdapter extends CursorAdapter {


    public RetrieveDevicesAdapter(Context context , Cursor cursor){


        super(context , cursor , false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        // if the list item is empty we create a new item and we choose what layout we want , pass the parent which is the list item  , and pass false
        return LayoutInflater.from(context).inflate(R.layout.support_simple_spinner_dropdown_item , parent , false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        //here we will format the list item by retrieving data from cursor

        //TODO


    }
}
