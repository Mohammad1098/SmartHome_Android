package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class RetrieveSpecificDeviceAdapter extends CursorAdapter {


    public RetrieveSpecificDeviceAdapter(Context context , Cursor cursor){


        super(context , cursor , false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }
}
