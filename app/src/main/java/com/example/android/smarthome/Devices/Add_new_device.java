package com.example.android.smarthome.Devices;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.smarthome.Adapters.Device_Category_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.R;
import java.util.ArrayList;



public class Add_new_device extends AppCompatActivity {

    private ArrayList<DeviceCategory> deviceCategoryList = new ArrayList<>();;

    private Device_Category_Spinner_Adapter device_category_spinner_adapter;

    private Spinner categorySpinner;

    private int selectedCategory;

    private EditText deviceNameEditText;

    private EditText devicePinEditText;

    private EditText deviceRoomEditText;

    private Button add_device_button;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_device);
        CreateViews();
        CreateSpinnerAdapter();
        attachSpinnerToListener();



    }



    private ArrayList<DeviceCategory> CreateDeviceCategory(){



        deviceCategoryList.add(new DeviceCategory("Light Bulb " , R.drawable.light_bulb_64 , 0 ));


        return deviceCategoryList;
    }


    private void CreateSpinnerAdapter(){

        device_category_spinner_adapter =  new Device_Category_Spinner_Adapter(this , CreateDeviceCategory());

        categorySpinner = findViewById(R.id.spinner_device_category_Lay_add_new_specific_device);

        categorySpinner.setAdapter(device_category_spinner_adapter);

    }


    private void attachSpinnerToListener(){


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DeviceCategory selectedDevice = (DeviceCategory) parent.getItemAtPosition(position);

                selectedCategory = selectedDevice.getType(); // 0 Light Bulb



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = -1;

            }
        });


    }

    private void CreateViews(){

         deviceNameEditText = findViewById(R.id.device_name_Lay_add_new_specific_device);

         devicePinEditText = findViewById(R.id.device_pin_Lay_add_new_specific_device);

         deviceRoomEditText = findViewById(R.id.device_room_Lay_add_new_specific_device);




         add_device_button = findViewById(R.id.add_device_button_Lay_add_new_device);

        add_device_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDeviceToDB();
            }
        });


    }



    private void addDeviceToDB(){

        String deviceName = deviceNameEditText.getText().toString().trim();

        String devicePin = devicePinEditText.getText().toString().trim();

        String deviceRoom = deviceRoomEditText.getText().toString().trim();


        if(TextUtils.isEmpty(deviceName) ){

            Toast.makeText(getApplicationContext() , "Please write the Device Name" , Toast.LENGTH_LONG).show();
            return;

        }

        if(TextUtils.isEmpty(devicePin)){

            Toast.makeText(getApplicationContext() , "Please write the Device Pin" , Toast.LENGTH_LONG).show();
            return;

        }

        if(selectedCategory == -1){

            Toast.makeText(getApplicationContext() , "Please select Device Category" , Toast.LENGTH_LONG).show();
            return;

        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(Schema.Device.NAME , deviceName);
        contentValues.put(Schema.Device.PIN , devicePin);
        contentValues.put(Schema.Device.ROOM , deviceRoom);
        contentValues.put(Schema.Device.TYPE , selectedCategory);

        getContentResolver().insert(Schema.Device.CONTENT_URI, contentValues);

        finish();


    }



}
