package com.example.android.smarthome.MicroController;


import android.app.Activity;
import android.view.View;
import android.widget.GridView;
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
    private GridView gridView;

    public RetrieveListOfMicroController_Controller(Activity activity , GridView gridView){

        this.activity = activity;
        this.gridView = gridView;

    }

    public boolean retrieveMicroControllers (){



        retrieveListOfMicroControllerDA = new RetrieveListOfMicroControllerDA(this.activity);
        ArrayList<MicroController> microControllers = retrieveListOfMicroControllerDA.retrieveMicroControllersDA();

        if(microControllers != null) {
            retrieveListOfMicroControllerAdapter = new RetrieveListOfMicroControllerAdapter(activity.getApplicationContext(), microControllers);
            gridView.setAdapter(retrieveListOfMicroControllerAdapter);
            return true;
        }
        else{

            return false;
        }





    }

}
