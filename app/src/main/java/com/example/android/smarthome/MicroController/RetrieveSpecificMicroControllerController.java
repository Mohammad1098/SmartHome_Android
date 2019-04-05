package com.example.android.smarthome.MicroController;

import android.app.Activity;

public class RetrieveSpecificMicroControllerController {


    private RetrieveSpecificMicroControllerDA retrieveSpecificMicroControllerDA;

    private Activity activity;
    private long MicroControllerId;
    public RetrieveSpecificMicroControllerController(Activity activity , long MicroControllerId){

        this.activity = activity;
        this.MicroControllerId= MicroControllerId;

    }

    public void TEST_displaySpecificMicroControllerTable(){


        retrieveSpecificMicroControllerDA= new RetrieveSpecificMicroControllerDA(this.activity , MicroControllerId);

        retrieveSpecificMicroControllerDA.TEST_displaySpecificMicroControllerTable();

    }
}
