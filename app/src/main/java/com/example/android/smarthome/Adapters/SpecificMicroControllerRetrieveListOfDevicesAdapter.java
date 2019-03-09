package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.smarthome.Devices.Device;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class SpecificMicroControllerRetrieveListOfDevicesAdapter extends ArrayAdapter<Device> {


    public SpecificMicroControllerRetrieveListOfDevicesAdapter(Context context , ArrayList<Device> list){

        super(context , 0 , list);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_list_item, parent, false);
        }

            return  null;
    }
}
