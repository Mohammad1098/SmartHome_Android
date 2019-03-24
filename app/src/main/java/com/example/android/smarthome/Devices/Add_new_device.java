package com.example.android.smarthome.Devices;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Pins.RetrieveListOfPinsController;
import com.example.android.smarthome.R;
import java.util.ArrayList;


public class Add_new_device extends AppCompatActivity {

    private long MicroControllerID;

    private int selectedPins,type;

    private Spinner categorySpinner;

    private ArrayList<ContentValues> contentValuesArrayList;

    private RetrieveListOfPinsController retrieveListOfPinsController;

    private EditText deviceNameEditText,deviceRoomEditText,firstPinEditText,secondPinEditText,thirdPinEditText;

    private Button add_device_button;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_device);

        CreateViews();
        CreatePinSpinner();
        attachSpinnerToListener();



    }


//    submit button for add

    private void CreatePinSpinner(){

        String pinSpinner[] = {"1","2","3"};

        // Selection of the spinner

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, pinSpinner);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerArrayAdapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_check, menu);
        return true;
    }


    private void attachSpinnerToListener(){


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        selectedPins=1; // means 1 pin
                        createAppropriateEditText(selectedPins);
                        break;
                    case 1:
                        selectedPins=2; // means 2 pin
                        createAppropriateEditText(selectedPins);
                        break;
                    case 2:
                        selectedPins=3; // means 3 pin
                        createAppropriateEditText(selectedPins);
                        break;


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPins = -1;

            }
        });


    }



    private void CreateViews(){


        Intent intent = getIntent();

        type = intent.getIntExtra("TYPE" , -1);

        MicroControllerID = intent.getIntExtra("MICROCONTROLLER_ID" , -1);

        categorySpinner = findViewById(R.id.spinner_number_of_pins_Lay_add_new_specific_device);

        deviceNameEditText = findViewById(R.id.device_name_Lay_add_new_specific_device);

        deviceRoomEditText = findViewById(R.id.device_room_Lay_add_new_specific_device);

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



    }



    private void addDeviceToDB(){

        if(selectedPins == -1){

            // means the user did't select any shield
            Toast.makeText(this , "Please Select number of pins" , Toast.LENGTH_LONG).show();
            return;
        }


        String deviceName = deviceNameEditText.getText().toString().trim();

        if(TextUtils.isEmpty(deviceName) ){

            Toast.makeText(getApplicationContext() , "Please write the Device Name" , Toast.LENGTH_LONG).show();
            return;

        }

        String deviceRoom = deviceRoomEditText.getText().toString().trim();


        if(TextUtils.isEmpty(deviceRoom)){

            Toast.makeText(getApplicationContext() , "Please write the Device Room" , Toast.LENGTH_LONG).show();
            return;

        }

        boolean availability = checkAvailabilityOfPins(selectedPins);

        if(!availability){

            return;

        }

        ArrayList<ContentValues> contentValuesList = returnAppropriateContentValues(selectedPins);

        ContentValues contentValues =  contentValuesList.get(0);  // which is the contentValues that contains Device Info
        ContentValues contentValues2 = contentValuesList.get(1);


        contentValues.put(Schema.Device.NAME , deviceName);
        contentValues.put(Schema.Device.ROOM , deviceRoom);
        contentValues.put(Schema.Device.TYPE , 10);
        contentValues.put(Schema.Device.MICROCONTROLLER_ID , MicroControllerID);

        contentValues2.put(Schema.Pin.MICROCONTROLLER_ID ,MicroControllerID);
        contentValues2.put(Schema.Pin.TYPE ,10);

        getContentResolver().insert(Schema.Device.CONTENT_URI, contentValues);
        getContentResolver().update(Schema.Pin.CONTENT_URI, contentValues2  , null , null); //contentValues2 which is the contentValues that contains Pin Info

        returnToPreviousLayout();


    }



    private void returnToPreviousLayout(){

        Intent openSpecificDeviceLayoutIntent = new Intent(Add_new_device.this, RetrieveSpecificDeviceBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openSpecificDeviceLayoutIntent.putExtra("TYPE", type);
        openSpecificDeviceLayoutIntent.putExtra("MICROCONTROLLER_ID", MicroControllerID);


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


    private void createAppropriateEditText(int number_of_pins){


        switch (number_of_pins){


            case 1 :
                firstPinEditText.setVisibility(View.VISIBLE);
                secondPinEditText.setVisibility(View.GONE);
                thirdPinEditText.setVisibility(View.GONE);
                break;

            case 2 :
                firstPinEditText.setVisibility(View.VISIBLE);
                secondPinEditText.setVisibility(View.VISIBLE);
                thirdPinEditText.setVisibility(View.GONE);
                break;

            case 3 :
                firstPinEditText.setVisibility(View.VISIBLE);
                secondPinEditText.setVisibility(View.VISIBLE);
                thirdPinEditText.setVisibility(View.VISIBLE);
                break;


        }


    }


    private boolean checkAvailabilityOfPins(int number_of_pins){

        // create list to contain the pins and send it to another method to check availability (checkAvailabilityOfPinsInDA)
        ArrayList<Integer> pins = new ArrayList<>();

        if(number_of_pins == 1){


            String pin1 = firstPinEditText.getText().toString().trim();


            if(TextUtils.isEmpty(pin1)){
                Toast.makeText(getApplicationContext() , "Please write the Device Pin 1" , Toast.LENGTH_LONG).show();
                return false;
            }

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));

            return checkAvailabilityOfPinsInDA(pins);

        }


        if(number_of_pins == 2){

            String pin1 = firstPinEditText.getText().toString().trim();
            String pin2 = secondPinEditText.getText().toString().trim();

            if(TextUtils.isEmpty(pin1) || TextUtils.isEmpty(pin2)  ){

                Toast.makeText(getApplicationContext() , "Please write the Device Pin 1&2" , Toast.LENGTH_LONG).show();
                return false;

            }

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));
            pins.add(Integer.valueOf(pin2));

            return checkAvailabilityOfPinsInDA(pins);

        }

        if(number_of_pins == 3){


            String pin1 = firstPinEditText.getText().toString().trim();
            String pin2 = secondPinEditText.getText().toString().trim();
            String pin3 = thirdPinEditText.getText().toString().trim();

            if(TextUtils.isEmpty(pin1) || TextUtils.isEmpty(pin2) || TextUtils.isEmpty(pin3) ){

                Toast.makeText(getApplicationContext() , "Please write the Device Pin 1&2&3" , Toast.LENGTH_LONG).show();
                return false;

            }

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));
            pins.add(Integer.valueOf(pin2));
            pins.add(Integer.valueOf(pin3));


            return checkAvailabilityOfPinsInDA(pins);

        }

        return false;


    }

    private boolean checkAvailabilityOfPinsInDA(ArrayList<Integer> pins){


        retrieveListOfPinsController = new RetrieveListOfPinsController(this);

        Log.e("add new device check Da" , String.valueOf(pins.get(0)));

        boolean valid_pin_number = retrieveListOfPinsController.checkAvailabilityOfPins(pins ,MicroControllerID);
        if(valid_pin_number){
            Log.e("add new device check Da" , "True");

            return true;
        }
        else {
            Log.e("add new device check Da" , "False");

            Toast.makeText(this , "Please enter Valid pin number" , Toast.LENGTH_LONG).show();

            return false;

        }
    }


    private ArrayList<ContentValues> returnAppropriateContentValues(int number_of_pins){

        contentValuesArrayList = new ArrayList<>();

        ContentValues contentValues_DeviceTable = new ContentValues();

        ContentValues contentValues_PinTable = new ContentValues(); // To update pin table


        if(number_of_pins == 1){

            String pin1 = firstPinEditText.getText().toString().trim();

            Integer pinNumber1 = Integer.valueOf(pin1);

            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber1);
            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber1);


        }

        if(number_of_pins == 2){

            String pin1 = firstPinEditText.getText().toString().trim();
            String pin2 = secondPinEditText.getText().toString().trim();


            Integer pinNumber1 = Integer.valueOf(pin1);
            Integer pinNumber2 = Integer.valueOf(pin2);


            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber1);
            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber2);

            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber1);
            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber2);



        }

        if(number_of_pins == 3){

            String pin1 = firstPinEditText.getText().toString().trim();
            String pin2 = secondPinEditText.getText().toString().trim();
            String pin3 = thirdPinEditText.getText().toString().trim();


            Integer pinNumber1 = Integer.valueOf(pin1);
            Integer pinNumber2 = Integer.valueOf(pin2);
            Integer pinNumber3 = Integer.valueOf(pin3);


            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber1);
            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber2);
            contentValues_PinTable.put(Schema.Pin.PIN_NUMBER , pinNumber3);

            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber1);
            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber2);
            contentValues_DeviceTable.put(Schema.Device.PIN , pinNumber3);


        }

        contentValuesArrayList.add(contentValues_DeviceTable);
        contentValuesArrayList.add(contentValues_PinTable);

        return  contentValuesArrayList;


    }


}
