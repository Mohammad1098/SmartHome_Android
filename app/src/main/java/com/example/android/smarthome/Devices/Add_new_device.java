package com.example.android.smarthome.Devices;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.android.smarthome.Adapters.Device_Category_Spinner_Adapter;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.R;
import java.util.ArrayList;



public class Add_new_device extends AppCompatActivity {

    private ArrayList<DeviceCategory> deviceCategoryList;
    private Device_Category_Spinner_Adapter device_category_spinner_adapter;
    private Spinner categorySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_device);

        device_category_spinner_adapter =  new Device_Category_Spinner_Adapter(this , CreateDeviceCategory());

        categorySpinner = findViewById(R.id.spinner_device_category_Lay_add_new_specific_device);

        categorySpinner.setAdapter(device_category_spinner_adapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        // TODO identify which category selected




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO identify which category selected

            }
        });


    }



    private ArrayList<DeviceCategory> CreateDeviceCategory(){


        deviceCategoryList.add(new DeviceCategory("Light Bulb " , R.drawable.light_bulb_64));


        return deviceCategoryList;
    }
}
