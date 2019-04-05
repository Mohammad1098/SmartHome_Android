package com.example.android.smarthome.MicroController;


import android.app.Activity;
import android.widget.GridView;

import com.example.android.smarthome.Adapters.RetrieveListOfMicroControllerAdapter;

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

    public RetrieveListOfMicroController_Controller(Activity activity ){

        this.activity = activity;

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


    public int returnMicroControllerType(long MicroControllerId){

        retrieveListOfMicroControllerDA = new RetrieveListOfMicroControllerDA(this.activity);

        return retrieveListOfMicroControllerDA.returnMicroControllerType(MicroControllerId);

    }

}
