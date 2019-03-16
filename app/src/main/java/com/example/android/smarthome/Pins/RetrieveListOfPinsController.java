package com.example.android.smarthome.Pins;

import android.app.Activity;

import com.example.android.smarthome.Devices.RetrieveListOfDevicesDA;

import java.util.ArrayList;

public class RetrieveListOfPinsController {

    private RetrieveListOfPinsDA retrieveListOfPinsDA;
    private Activity activity;

    public RetrieveListOfPinsController(Activity activity){

        this.activity = activity;

    }

    public boolean checkAvailabilityOfPins(ArrayList<Integer> pins , long MicroController_ID){

        retrieveListOfPinsDA = new RetrieveListOfPinsDA(this.activity,MicroController_ID);

        return retrieveListOfPinsDA.checkAvailabilityOfPins(pins);

    }

}
