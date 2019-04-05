package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveDevicesAdapter;

import java.util.ArrayList;




public class RetrieveListOfDevicesController extends AppCompatActivity {

    private RetrieveListOfDevicesDA retrieveListOfDevicesDA;
    private RetrieveDevicesAdapter retrieveListOfDevicesAdapter;
    private Activity activity;
    private ListView listView;

    public RetrieveListOfDevicesController(Activity activity , ListView listView ){

        this.activity = activity;
        this.listView = listView;
    }




    public boolean retrieveDevices (){


        retrieveListOfDevicesDA = new RetrieveListOfDevicesDA(this.activity);
        ArrayList<Device> devicesList = retrieveListOfDevicesDA.retrieveDevicesDA();

        if(devicesList != null) {
            retrieveListOfDevicesAdapter = new RetrieveDevicesAdapter(activity.getApplicationContext(), devicesList);
            listView.setAdapter(retrieveListOfDevicesAdapter);
            return true;
        }
        else{

            return false;
        }





    }



}
