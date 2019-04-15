package com.example.android.smarthome.IR_Devices;

import android.app.Activity;
import android.widget.ListView;
import com.example.android.smarthome.Adapters.RetrieveListOfOperationAdapter;
import com.example.android.smarthome.Operation.Operation;

import java.util.ArrayList;

public class IR_Devices_Controller {


    private IR_Devices_DA irDevicesDa;
    private RetrieveListOfOperationAdapter retrieveListOfOperationAdapter;
    private Activity activity;
    private ListView OperationList;

    public IR_Devices_Controller(Activity activity , ListView listView){

        this.activity = activity;
        this.OperationList = listView;

    }

    public boolean retrieveOperation(long MicroControllerId , long deviceId ){



        irDevicesDa = new IR_Devices_DA(this.activity);
        ArrayList<Operation> operations = irDevicesDa.retrieveOperation(MicroControllerId , deviceId);

        if(operations != null) {
            retrieveListOfOperationAdapter = new RetrieveListOfOperationAdapter(activity.getApplicationContext(), operations);
            OperationList.setAdapter(retrieveListOfOperationAdapter);
            return true;
        }
        else{

            return false;
        }



    }
}
