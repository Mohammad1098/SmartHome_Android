package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveDevicesAdapter;

import java.util.ArrayList;

public class RetrieveMicroControllerDevicesController extends AppCompatActivity {


    private RetrieveMicroControllerDevicesDA retrieveMicroControllerDevicesDA;
    private RetrieveDevicesAdapter retrieveMicroControllerDevicesAdapter;
    private Activity activity;
    private ListView listView;




    public RetrieveMicroControllerDevicesController(Activity activity , ListView listView ) {

        this.activity = activity;
        this.listView = listView;

    }


    public boolean retrieveMicroControllerDevices (long MicroControllerId){

        retrieveMicroControllerDevicesDA = new RetrieveMicroControllerDevicesDA(this.activity );

        ArrayList<Device> MicroControllerDevicesList = retrieveMicroControllerDevicesDA.retrieveMicroControllerDevicesByMicroController(MicroControllerId);


        if(MicroControllerDevicesList != null) {
            retrieveMicroControllerDevicesAdapter = new RetrieveDevicesAdapter(activity.getApplicationContext(), MicroControllerDevicesList);
            listView.setAdapter(retrieveMicroControllerDevicesAdapter);
            return true;
        }
        else{

            return false;
        }

    }




}
