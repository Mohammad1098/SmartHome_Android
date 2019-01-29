package com.example.android.smarthome.Adapters;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.R;

import org.w3c.dom.Text;

// this class will use to list the all Device in the system
public class RetrieveListOfDevicesAdapter extends CursorAdapter {

    public RetrieveListOfDevicesAdapter(Context context , Cursor cursor , String deviceType){


        super(context , cursor , false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {






    }
}
