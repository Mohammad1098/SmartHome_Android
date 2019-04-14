package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.widget.GridView;

import com.example.android.smarthome.Adapters.RetrieveListOfDevicesAdapter;
import com.example.android.smarthome.Adapters.RetrieveListOfMicroControllerAdapter;
import com.example.android.smarthome.MicroController.MicroController;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroControllerDA;

import java.util.ArrayList;

public class RetrieveDevicesController {

    private RetrieveDevicesDA retrieveDevicesDA;
    private RetrieveListOfDevicesAdapter retrieveListOfDevicesAdapter;
    private Activity activity;
    private GridView gridView;

    public RetrieveDevicesController(Activity activity , GridView gridView){

        this.activity = activity;
        this.gridView = gridView;

    }

    public boolean retrieveDevices(long MicroControllerId , int deviceType ){



        retrieveDevicesDA = new RetrieveDevicesDA(this.activity);
        ArrayList<Device> devices = retrieveDevicesDA.retrieveDevices(MicroControllerId , deviceType);

        if(devices != null) {
            retrieveListOfDevicesAdapter = new RetrieveListOfDevicesAdapter(activity.getApplicationContext(), devices);
            gridView.setAdapter(retrieveListOfDevicesAdapter);
            return true;
        }
        else{

            return false;
        }



    }

}
