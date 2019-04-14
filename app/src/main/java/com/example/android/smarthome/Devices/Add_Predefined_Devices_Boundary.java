package com.example.android.smarthome.Devices;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.smarthome.Adapters.Device_Category_Spinner_Adapter;
import com.example.android.smarthome.Adapters.Shield_Category_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.Pins.RetrieveListOfPinsController;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.RetrieveShieldController;
import com.example.android.smarthome.Shield.ShieldCategory;

import java.util.ArrayList;

public class Add_Predefined_Devices_Boundary extends AppCompatActivity {


    private Spinner predefinedDevicesSpinner ,shieldSpinner;
    private long MicroControllerID;
    private EditText deviceNameEditText ,deviceRoomEditText,firstPinEditText;
    private Button add_device_button;
    private int selectedDevice , selectedPin=-1;
    private TextView shield_spinner_text;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_predefined_device);

        CreateViews();
        CreateDevicesSpinner();
        attachDeviceSpinnerToListener();
        returnSelectedPin();



    }

    private void CreateViews(){


        Intent intent = getIntent();


        MicroControllerID = intent.getLongExtra("MICROCONTROLLER_ID" , -1);

        predefinedDevicesSpinner = findViewById(R.id.spinner_device_category_Lay_add_new_predefined_device);

        shieldSpinner = findViewById(R.id.shield_spinner_Lay_add_new_predefined_device);

        shieldSpinner.setVisibility(View.GONE);

        deviceNameEditText = findViewById(R.id.device_name_Lay_add_new_predefined_device);

        deviceRoomEditText = findViewById(R.id.device_room_Lay_add_new_predefined_device);

        add_device_button = findViewById(R.id.add_device_button_Lay_add_new_predefined_device);

        shield_spinner_text = findViewById(R.id.shield_spinner_text_Lay_add_new_predefined_device);

        shield_spinner_text.setVisibility(View.GONE);

        firstPinEditText = findViewById(R.id.first_pin_Lay_add_new_predefined_device);

        firstPinEditText.setVisibility(View.GONE);

        add_device_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDeviceToDB();
            }
        });



    }



    private void CreateDevicesSpinner(){

        Add_Predefined_Devices_Controller controller = new Add_Predefined_Devices_Controller(Add_Predefined_Devices_Boundary.this);


        predefinedDevicesSpinner.setAdapter(controller.CreateDevicesCategorySpinner());

    }


    private void CreateAppropriateShieldSpinner(){


        switch (selectedDevice){

            case 0 :
                CreateRelaySpinner(); //means Light bulb then we need to create relay spinner
                break;

            case 1 :
                Create_RGB_LED_Spinner();
                break;

            case 2 :
                CreateIRSpinner();  //TV we need IR spinner
                break;

            case 3 :
                CreateIRSpinner();  //Receiver we need IR spinner
                break;

            case 4 :
                CreateIRSpinner(); //AC we need IR spinner
                break;

            case 5 :
                //CreateRelaySpinner();  //Other we need IR spinner
                break;
        }


    }




    private void CreateRelaySpinner(){

        shield_spinner_text.setVisibility(View.VISIBLE);
        shieldSpinner.setVisibility(View.GONE);
        firstPinEditText.setVisibility(View.GONE);

        Add_Predefined_Devices_Controller controller = new Add_Predefined_Devices_Controller(Add_Predefined_Devices_Boundary.this);


        if (controller.returnRelaySpinner(MicroControllerID) != null){


            shieldSpinner.setAdapter(controller.returnRelaySpinner(MicroControllerID));

        }

        else{


            shield_spinner_text.setText("No Relay Available");
        }

    }



    private void CreateIRSpinner(){

        shield_spinner_text.setVisibility(View.VISIBLE);
        shieldSpinner.setVisibility(View.GONE);
        firstPinEditText.setVisibility(View.GONE);

        Add_Predefined_Devices_Controller controller = new Add_Predefined_Devices_Controller(Add_Predefined_Devices_Boundary.this);


        if (controller.returnIRSpinner(MicroControllerID) != null){



            shieldSpinner.setAdapter(controller.returnIRSpinner(MicroControllerID));

        }
        else{
            shield_spinner_text.setText("No IR Available");

        }

    }


    private void Create_RGB_LED_Spinner(){

        shieldSpinner.setVisibility(View.GONE);
        shield_spinner_text.setVisibility(View.GONE);
        firstPinEditText.setVisibility(View.VISIBLE);


    }


    private int check_RGB_LED_PinSelected(){


        Add_Predefined_Devices_Controller controller = new Add_Predefined_Devices_Controller(Add_Predefined_Devices_Boundary.this);

        return controller.check_RGB_LED_PinSelected(firstPinEditText , MicroControllerID);
    }



    private void returnSelectedPin(){

        shieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ShieldCategory currentShiled = (ShieldCategory) parent.getItemAtPosition(position);

                selectedPin = currentShiled.getPin();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                selectedPin=-1;
            }
        });

    }




    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_check, menu);
        //addDeviceToDB();
        return true;
    }


    private void attachDeviceSpinnerToListener(){


        predefinedDevicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        selectedDevice=0; // means Light bulb
                        break;
                    case 1:
                        selectedDevice=1; // means RGB LED
                        break;
                    case 2:
                        selectedDevice=2; // means TV
                        break;
                    case 3:
                        selectedDevice=3; // means Receiver
                        break;
                    case 4:
                        selectedDevice=4; // means AC
                        break;
                    case 5:
                        selectedDevice=5; // means OTHER
                        break;


                }

                CreateAppropriateShieldSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDevice = -1;

            }
        });


    }




    private void addDeviceToDB(){

        if(selectedDevice == -1){

            // means the user did't select any shield
            Toast.makeText(this , "Please Select device category" , Toast.LENGTH_LONG).show();
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



        ContentValues DeviceContent = new ContentValues();

        if(selectedDevice == 0) { //means LIGHT BULB

            DeviceContent.put(Schema.Device.NAME , "Light Bulb");

        }



        if(selectedDevice == 1) { //means RGB

            String pinAsString = firstPinEditText.getText().toString().trim();

            if(TextUtils.isEmpty(pinAsString)){

                Toast.makeText(getApplicationContext() , "Select valid pin for RGB " , Toast.LENGTH_LONG).show();

                return;
            }




            if(check_RGB_LED_PinSelected() !=-1){

                DeviceContent.put(Schema.Device.NAME , "W2812B RGB LED");

                selectedPin = check_RGB_LED_PinSelected();


            }

            else {
                Toast.makeText(getApplicationContext() , "Select valid pin for RGB " , Toast.LENGTH_LONG).show();
                return;
            }

        }


        if(selectedPin == -1){

            Toast.makeText(getApplicationContext() , "Please select valid pin" , Toast.LENGTH_LONG).show();
            return;

        }


        if(selectedDevice == 2) { //means TV


            DeviceContent.put(Schema.Device.NAME , "TV");


        }


        if(selectedDevice == 3) { //means RECEIVER


            DeviceContent.put(Schema.Device.NAME , "RECEIVER");


        }



        if(selectedDevice == 4) { //means AC


            DeviceContent.put(Schema.Device.NAME , "AC");


        }


        if(selectedDevice == 5) { //means OTHERS


            DeviceContent.put(Schema.Device.NAME , "OTHER");


        }



        DeviceContent.put(Schema.Device.TYPE , selectedDevice);
        DeviceContent.put(Schema.Device.ROOM , deviceRoom);
        DeviceContent.put(Schema.Device.TYPE , MicroControllerID);




        Uri uri = getContentResolver().insert(Schema.Device.CONTENT_URI, DeviceContent);

        long deviceId = ContentUris.parseId(uri);


        ContentValues updatePinTable = new ContentValues();

        updatePinTable.put(Schema.Pin.AVAILABILITY , 0);
        updatePinTable.put(Schema.Pin.DEVICE_ID , deviceId);

        getContentResolver().update(Schema.Pin.CONTENT_URI , updatePinTable , "PINNUMBER="+selectedPin , null);

        returnToPreviousLayout();


    }


    private void returnToPreviousLayout(){

        Intent openSpecificDeviceLayoutIntent = new Intent(Add_Predefined_Devices_Boundary.this, RetrieveMicroControllerDevicesBoundary.class);


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


}
