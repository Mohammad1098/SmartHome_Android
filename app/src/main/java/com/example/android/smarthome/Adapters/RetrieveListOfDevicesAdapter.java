package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.smarthome.Devices.Device;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class RetrieveListOfDevicesAdapter extends ArrayAdapter<Device> {


    public RetrieveListOfDevicesAdapter(Context context , ArrayList<Device> list){


        super(context , 0 , list);


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_list_item, parent , false);
        }


        Device currentDevice = getItem(position);


        TextView deviceName = (TextView) convertView.findViewById(R.id.device_name_Lay_device_list_item);

        deviceName.setText(currentDevice.getName());





        return convertView;


    }





}