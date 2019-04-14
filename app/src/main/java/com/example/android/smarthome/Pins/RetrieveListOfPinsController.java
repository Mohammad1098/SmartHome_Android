package com.example.android.smarthome.Pins;

import android.app.Activity;

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


    public boolean check_RGB_LED_Pins(int pin , long MicroController_ID){


        retrieveListOfPinsDA = new RetrieveListOfPinsDA(this.activity,MicroController_ID);

        return retrieveListOfPinsDA.check_RGB_LED_Pins(pin);

    }


    public String returnAvailableDigitalPin(long MicroControllerID){


        retrieveListOfPinsDA = new RetrieveListOfPinsDA(this.activity , MicroControllerID);


        return retrieveListOfPinsDA.returnAvailableDigitalPin();

    }


    public String returnAvailableAnalogPin(long MicroControllerID){

        retrieveListOfPinsDA = new RetrieveListOfPinsDA(this.activity , MicroControllerID);


        return retrieveListOfPinsDA.returnAvailableAnalogPin();

    }

    public int returnShieldPin(long MicroControllerID ,long ShieldID){


        retrieveListOfPinsDA = new RetrieveListOfPinsDA(this.activity , MicroControllerID);


        return retrieveListOfPinsDA.returnShieldPin(MicroControllerID , ShieldID);
    }

}
