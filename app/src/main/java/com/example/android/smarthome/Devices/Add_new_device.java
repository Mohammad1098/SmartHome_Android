package com.example.android.smarthome.Devices;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.smarthome.Adapters.Device_Category_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Operation.Add_new_operation;
import com.example.android.smarthome.Operation.RetrieveListOfOperationBoundary;
import com.example.android.smarthome.R;
import java.util.ArrayList;



public class Add_new_device extends AppCompatActivity {

    private ArrayList<DeviceCategory> deviceCategoryList = new ArrayList<>();;

    private Device_Category_Spinner_Adapter device_category_spinner_adapter;

    private Spinner categorySpinner;

    private int selectedCategory;

    private EditText deviceNameEditText;

    private EditText deviceRoomEditText;

    private Button add_device_button;

    private RadioButton onePinSelected;

    private RadioButton thereePinSelected;

    private EditText firstPinEditText;

    private EditText secondPinEditText;

    private EditText thirdPinEditText;

    private int type;

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


        Intent intent = getIntent();

        type = intent.getIntExtra("TYPE" , -1);

         deviceNameEditText = findViewById(R.id.device_name_Lay_add_new_specific_device);

         deviceRoomEditText = findViewById(R.id.device_room_Lay_add_new_specific_device);

         onePinSelected = findViewById(R.id.device_one_pin_selected_Lay_add_new_specific_device);

         thereePinSelected = findViewById(R.id.device_three_pins_selected_Lay_add_new_specific_device);

         firstPinEditText = findViewById(R.id.first_pin_Lay_add_new_specific_device);

         secondPinEditText = findViewById(R.id.second_pin_Lay_add_new_specific_device);

         thirdPinEditText = findViewById(R.id.third_pin_Lay_add_new_specific_device);

         firstPinEditText.setVisibility(View.GONE);

         secondPinEditText.setVisibility(View.GONE);

         thirdPinEditText.setVisibility(View.GONE);



        add_device_button = findViewById(R.id.add_device_button_Lay_add_new_device);

        add_device_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDeviceToDB();
            }
        });


        onePinSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstPinEditText.setVisibility(View.VISIBLE);

                secondPinEditText.setVisibility(View.GONE);

                thirdPinEditText.setVisibility(View.GONE);


            }
        });



        thereePinSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstPinEditText.setVisibility(View.VISIBLE);

                secondPinEditText.setVisibility(View.VISIBLE);

                thirdPinEditText.setVisibility(View.VISIBLE);

            }
        });


    }



    private void addDeviceToDB(){

        String deviceName = deviceNameEditText.getText().toString().trim();

        // TODO this must be change later
        String devicePin = firstPinEditText.getText().toString().trim();

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

        returnToPreviousLayout();


    }



    private void returnToPreviousLayout(){

        Intent openSpecificDeviceLayoutIntent = new Intent(Add_new_device.this, RetrieveSpecificDeviceBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openSpecificDeviceLayoutIntent.putExtra("TYPE", type);


        startActivity(openSpecificDeviceLayoutIntent);


    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        returnToPreviousLayout();

                    }
                }).create().show();


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                returnToPreviousLayout();

                            }
                        }).create().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
