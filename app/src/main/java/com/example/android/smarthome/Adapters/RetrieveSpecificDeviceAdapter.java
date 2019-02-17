package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Devices.Device;
import java.util.ArrayList;

// this class is used by any category of devices such as LightBulb , Tv , RGB LightBulb etc to present the list of the devices


public class RetrieveSpecificDeviceAdapter extends ArrayAdapter<Device> {


    public RetrieveSpecificDeviceAdapter(Context context , ArrayList<Device> list){


        super(context , 0 , list);


    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.specific_device_list_item, parent , false);
        }


        Device currentDevice = getItem(position);


        TextView deviceName = (TextView) convertView.findViewById(R.id.device_name_Lay_specific_device_list_item);

        deviceName.setText(currentDevice.getName());



        TextView deviceRoom = (TextView) convertView.findViewById(R.id.device_room_Lay_specific_device_list_item);

        deviceRoom.setText(currentDevice.getRoom());


        return convertView;


    }





}
