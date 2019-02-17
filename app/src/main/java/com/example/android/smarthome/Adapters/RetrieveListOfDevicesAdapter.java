package com.example.android.smarthome.Adapters;

import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Devices.Device;
import com.example.android.smarthome.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


// this class will use to list the all Device in the system
public class RetrieveListOfDevicesAdapter extends ArrayAdapter<DeviceCategory> {


    public RetrieveListOfDevicesAdapter(Context context , ArrayList<DeviceCategory> list){


        super(context , 0 , list);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_list_item, parent , false);
        }


        DeviceCategory currentDevice = getItem(position);


        TextView deviceName = (TextView) convertView.findViewById(R.id.device_name_Lay_device_list_item);

        deviceName.setText(currentDevice.getCategoryName());

        //TODO the Image as well

        return convertView;


    }


}
