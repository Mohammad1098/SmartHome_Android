package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveListOfDevicesAdapter;
import com.example.android.smarthome.Adapters.RetrieveSpecificDeviceAdapter;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class RetrieveSpecificDeviceController extends AppCompatActivity {


    private RetrieveSpecificDeviceDA retrieveSpecificDeviceDA;
    private RetrieveSpecificDeviceAdapter retrieveSpecificDeviceAdapter;
    private Activity activity;
    private ListView listView;




    public RetrieveSpecificDeviceController( Activity activity , ListView listView ) {

        this.activity = activity;
        this.listView = listView;

    }


    public boolean retrieveSpecificDevices (int type){

        retrieveSpecificDeviceDA = new RetrieveSpecificDeviceDA(this.activity , type);

        ArrayList<Device> specificDevicesList = retrieveSpecificDeviceDA.retrieveSpecificDevicesDA();


        if(specificDevicesList != null) {
            retrieveSpecificDeviceAdapter = new RetrieveSpecificDeviceAdapter(activity.getApplicationContext(), specificDevicesList);
            listView.setAdapter(retrieveSpecificDeviceAdapter);
            return true;
        }
        else{

            return false;
        }

    }



}
