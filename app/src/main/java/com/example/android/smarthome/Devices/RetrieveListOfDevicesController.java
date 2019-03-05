package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.example.android.smarthome.Adapters.RetrieveListOfDevicesAdapter;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import java.util.ArrayList;




public class RetrieveListOfDevicesController extends AppCompatActivity {

    private RetrieveListOfDevicesDA retrieveListOfDevicesDA;
    private RetrieveListOfDevicesAdapter retrieveListOfDevicesAdapter;
    private Activity activity;
    private ListView listView;

    public RetrieveListOfDevicesController(Activity activity , ListView listView ){

        this.activity = activity;
        this.listView = listView;
    }




    public boolean retrieveDevices (){

        //TODO WE HAVE TO CREATE INSTANCE OF THE ADAPTER AND DA TO TAKE THE LIST AND SEND IT BACK TO ADAPTER


        retrieveListOfDevicesDA = new RetrieveListOfDevicesDA(this.activity);
        ArrayList<DeviceCategory> devicesList = retrieveListOfDevicesDA.retrieveDevicesDA();

        if(devicesList != null) {
            retrieveListOfDevicesAdapter = new RetrieveListOfDevicesAdapter(activity.getApplicationContext(), devicesList);
            listView.setAdapter(retrieveListOfDevicesAdapter);
            return true;
        }
        else{

            return false;
        }





    }



}
