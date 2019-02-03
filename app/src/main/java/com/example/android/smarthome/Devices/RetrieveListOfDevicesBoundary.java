package com.example.android.smarthome.Devices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.smarthome.R;

import java.util.ArrayList;

public class RetrieveListOfDevicesBoundary extends AppCompatActivity {


    ListView deviceListView;
    ArrayList<Device> listOfDevices; // to get all type of devices

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_devices);


        deviceListView = findViewById(R.id.device_list_view_Lay_retrieve_list_of_devices);

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TODO we have to create instance of class RetrieveListDevicesDA to get all the devices existing in system


            }
        });
    }
}
