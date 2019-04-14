package com.example.android.smarthome.Devices;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

import com.example.android.smarthome.Adapters.Device_Category_Spinner_Adapter;
import com.example.android.smarthome.Adapters.Shield_Category_Spinner_Adapter;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Pins.RetrieveListOfPinsController;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.RetrieveShieldController;
import com.example.android.smarthome.Shield.ShieldCategory;

import java.util.ArrayList;

public class Add_Predefined_Devices_Controller {


    private Activity activity;

    public Add_Predefined_Devices_Controller(){


    }


    public Add_Predefined_Devices_Controller(Activity activity){

        this.activity = activity;

    }



    public Device_Category_Spinner_Adapter CreateDevicesCategorySpinner(){

        ArrayList<DeviceCategory> deviceCategoryArrayList = new ArrayList<>();

        deviceCategoryArrayList.add(new DeviceCategory("Light Bulb " , R.drawable.light_bulb_64 , 0 )); //0 ---->  Light Bulb   ,  1 ----> Rgb led strib ,  2 ----> TV  ,   3 ----> Receiver , 4 ----> AC  ,  5 ----> OTHER
        deviceCategoryArrayList.add(new DeviceCategory("Rgb LED Strip " , R.drawable.light_bulb_64 , 1 ));
        deviceCategoryArrayList.add(new DeviceCategory("TV " , R.drawable.light_bulb_64 , 2 ));
        deviceCategoryArrayList.add(new DeviceCategory("Receiver " , R.drawable.light_bulb_64 , 3 ));
        deviceCategoryArrayList.add(new DeviceCategory("AC " , R.drawable.light_bulb_64 , 4 ));
        deviceCategoryArrayList.add(new DeviceCategory("OTHER " , R.drawable.light_bulb_64 , 5 ));

        Device_Category_Spinner_Adapter deviceCategorySpinnerAdapter = new Device_Category_Spinner_Adapter(this.activity , deviceCategoryArrayList);


        return deviceCategorySpinnerAdapter;

    }


    public Shield_Category_Spinner_Adapter returnRelaySpinner(long MicroControllerID){

        RetrieveShieldController retrieveShieldController = new  RetrieveShieldController(this.activity );

        ArrayList<ShieldCategory> RelayList  = retrieveShieldController.returnRelayListSpinner(MicroControllerID);






        if(RelayList.size() != 0 ){

            Shield_Category_Spinner_Adapter relayShieldSpinnerAdapter = new Shield_Category_Spinner_Adapter(this.activity ,RelayList );

            return relayShieldSpinnerAdapter;
        }


        return null;
    }


    public Shield_Category_Spinner_Adapter returnIRSpinner(long MicroControllerID){


        RetrieveShieldController retrieveShieldController = new  RetrieveShieldController(this.activity );

        ArrayList<ShieldCategory> IRList  = retrieveShieldController.returnIRListListSpinner(MicroControllerID);

        if(IRList.size() != 0 ){

            Shield_Category_Spinner_Adapter IRShieldSpinnerAdapter = new Shield_Category_Spinner_Adapter(this.activity , IRList);


            return IRShieldSpinnerAdapter;
        }


        return null;
    }

    public int check_RGB_LED_PinSelected(EditText firstPinEditText , long MicroControllerID){


        int pin = Integer.parseInt(firstPinEditText.getText().toString().trim());

        RetrieveListOfPinsController retrieveListOfPinsController = new RetrieveListOfPinsController(this.activity);

        if(retrieveListOfPinsController.check_RGB_LED_Pins(pin ,MicroControllerID)){

            return pin;
        }
        else {
            return -1;
        }
    }


}
