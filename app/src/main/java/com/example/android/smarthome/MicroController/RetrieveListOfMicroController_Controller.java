package com.example.android.smarthome.MicroController;


import android.app.Activity;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveListOfDevicesAdapter;
import com.example.android.smarthome.Adapters.RetrieveListOfMicroControllerAdapter;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Devices.RetrieveListOfDevicesDA;

import java.util.ArrayList;

public class RetrieveListOfMicroController_Controller {


    private RetrieveListOfMicroControllerDA retrieveListOfMicroControllerDA;
    private RetrieveListOfMicroControllerAdapter retrieveListOfMicroControllerAdapter;
    private Activity activity;
    private ListView listView;

    public RetrieveListOfMicroController_Controller(Activity activity , ListView listView){

        this.activity = activity;
        this.listView = listView;

    }

    public boolean retrieveMicroControllers (){



        retrieveListOfMicroControllerDA = new RetrieveListOfMicroControllerDA(this.activity);
        ArrayList<MicroController> microControllers = retrieveListOfMicroControllerDA.retrieveMicroControllersDA();

        if(microControllers != null) {
            retrieveListOfMicroControllerAdapter = new RetrieveListOfMicroControllerAdapter(activity.getApplicationContext(), microControllers);
            listView.setAdapter(retrieveListOfMicroControllerAdapter);
            return true;
        }
        else{

            return false;
        }





    }

}
