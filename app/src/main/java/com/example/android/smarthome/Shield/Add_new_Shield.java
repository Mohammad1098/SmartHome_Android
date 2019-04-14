package com.example.android.smarthome.Shield;

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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.smarthome.Adapters.Shield_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Pins.RetrieveListOfPinsController;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class Add_new_Shield extends AppCompatActivity {


    private RetrieveListOfPinsController retrieveListOfPinsController;

    private long Microcontroller_ID;

    private Spinner shieldCategorySpinner;

    private LinearLayout LinearLayout_relay_channel;

    private EditText relayPin;

    private EditText irPin;

    private int selectedShield=-1;

    private Button btn_AddShield;

    private TextView availableDigitalPin , availableAnalogPin;

    private RadioButton DigitalPin , AnalogPin;



    @Override
    protected void onCreate(Bundle sa){

        super.onCreate(sa);
        setContentView(R.layout.add_new_shield);

        createView();
        attachShieldCategorySpinnerToListener();
    }

    @Override
    public void onResume(){
        super.onResume();
        createView();
        attachShieldCategorySpinnerToListener();

    }



    private void createView(){

        setTitle("Add new Shield");


        Intent previousIntent = getIntent();

        Microcontroller_ID = previousIntent.getLongExtra("MICROCONTROLLER_ID", -1);


        availableDigitalPin = findViewById(R.id.available_digital_pin_Lay_add_new_shield);

        availableDigitalPin.setText("Available Digital Pins "+returnAvailableDigitalPin());

        availableAnalogPin = findViewById(R.id.available_Analog_pin_Lay_add_new_shield);

        availableAnalogPin.setText("Available Analog Pins "+returnAvailableAnalogPin());


        DigitalPin = findViewById(R.id.Digital_pin_Lay_add_new_shield);

        AnalogPin = findViewById(R.id.Analog_pin_Lay_add_new_shield);

        shieldCategorySpinner = findViewById(R.id.spinner_Shield_Lay_add_new_shield);

        // create shields in createShieldCategorySpinnerItems method  and  set it to the spinner
        shieldCategorySpinner.setAdapter(createShieldCategorySpinnerItems());

        btn_AddShield = findViewById(R.id.add_shield_Lay_add_new_shield);

        btn_AddShield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addShieldToDB();
            }
        });

        LinearLayout_relay_channel= findViewById(R.id.LinearLayout_relay_channel_Lay_add_new_shield);

        relayPin = findViewById(R.id.relay_channel_1_Lay_add_new_shield);

        irPin = findViewById(R.id.IR_Pin_Lay_add_new_shield);

        LinearLayout_relay_channel.setVisibility(View.GONE);

        relayPin.setVisibility(View.GONE);

        irPin.setVisibility(View.GONE);

    }






    private void attachShieldCategorySpinnerToListener(){

        shieldCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Shield selectedShieldObj = (Shield) parent.getItemAtPosition(position);

                selectedShield = selectedShieldObj.getType(); // 1 for Relay , 2 for  IR

                createAppropriateEditText(selectedShield);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedShield = -1;

            }
        });

    }



    private Shield_Spinner_Adapter createShieldCategorySpinnerItems(){


        ArrayList<Shield> shieldList = new ArrayList();


        shieldList.add(new Shield("Relay" , R.drawable.relay , 1 )); // 1 for Relay 1 CHANNEL
        shieldList.add(new Shield("IR" , R.drawable.ir , 2)); //2 for IR

        Shield_Spinner_Adapter shieldSpinnerAdapter = new Shield_Spinner_Adapter(this , shieldList);


        return shieldSpinnerAdapter;
    }





    private void createAppropriateEditText(int type_of_Shield){


        if(type_of_Shield == 1){

            LinearLayout_relay_channel.setVisibility(View.VISIBLE);
            relayPin.setVisibility(View.VISIBLE);
            irPin.setVisibility(View.GONE);


        }

        if(type_of_Shield == 2){
            LinearLayout_relay_channel.setVisibility(View.VISIBLE);
            relayPin.setVisibility(View.GONE);
            irPin.setVisibility(View.VISIBLE);

        }


    }



    private void addShieldToDB(){


        if(selectedShield == -1){

            // means the user did't select any shield
            Toast.makeText(this , "Please Select Shield" , Toast.LENGTH_LONG).show();
            return;
        }


        ContentValues contentValues = new ContentValues(); //SHIELD TABLE

        Integer pinNumber = -1;

        if(selectedShield == 1){

            contentValues.put(Schema.Shield.NAME , "Relay");

            String relay_pin = relayPin.getText().toString().trim();


            if(TextUtils.isEmpty(relay_pin)){
                Toast.makeText(this , "Please enter pin number" , Toast.LENGTH_LONG).show();
                return;
            }

            pinNumber = Integer.valueOf(relay_pin);



        }


        if(selectedShield == 2){

            contentValues.put(Schema.Shield.NAME , "IR");

            String ir_pin = irPin.getText().toString().trim();


            if(TextUtils.isEmpty(ir_pin)){
                Toast.makeText(this , "Please enter pin number" , Toast.LENGTH_LONG).show();
                return;
            }

            pinNumber = Integer.valueOf(ir_pin);



        }


        boolean availability = checkAvailabilityOfPins(selectedShield);



        if(!availability){

            Toast.makeText(this , "Please enter Valid pin number" , Toast.LENGTH_LONG).show();
            return;

        }

        if(!DigitalPin.isChecked() && !AnalogPin.isChecked()){

            Toast.makeText(this , "Please select type of pin " , Toast.LENGTH_LONG).show();
            return;

        }


        //SHIELD TABLE
        contentValues.put(Schema.Shield.MICROCONTROLLER_ID , Microcontroller_ID);
        contentValues.put(Schema.Shield.TYPE , selectedShield);      // 1 relay  , 2 Ir
        contentValues.put(Schema.Shield.AVAILABILITY , 0);      // 0 free to use   , 1 has already used by device else
        Uri uri = getContentResolver().insert(Schema.Shield.CONTENT_URI, contentValues);

        long shieldID = ContentUris.parseId(uri);


        int type=-1;

        if(DigitalPin.isChecked()){

            type=0;
        }

        if(AnalogPin.isChecked()){

            type=1;
        }



        //PIN TABLE
        ContentValues contentValues1_PinTable = new ContentValues(); //PIN TABLE

        contentValues1_PinTable.put(Schema.Pin.AVAILABILITY , 0);
        contentValues1_PinTable.put(Schema.Pin.SHIELD_ID , shieldID);



        getContentResolver().update(Schema.Pin.CONTENT_URI, contentValues1_PinTable  , "PINNUMBER="+pinNumber+" AND MICROCONTROLLERID="+Microcontroller_ID+" AND TYPE="+type , null);





        returnToPreviousLayout();


    }



    private boolean checkAvailabilityOfPins(int type_of_Shield){

        // create list to contain the pins and send it to another method to check availability (checkAvailabilityOfPinsInDA)
        ArrayList<Integer> pins = new ArrayList<>();

        if(type_of_Shield == 1){


            String relay_pin = relayPin.getText().toString().trim();

            if(TextUtils.isEmpty(relay_pin)){

                return false;

            }

            // convert String to Integer
            pins.add(Integer.valueOf(relay_pin));

            return checkAvailabilityOfPinsInDA(pins);

        }


        if(type_of_Shield == 2){


            String ir_pin = irPin.getText().toString().trim();

            if(TextUtils.isEmpty(ir_pin)){

                return false;

            }

            // convert String to Integer
            pins.add(Integer.valueOf(ir_pin));

            return checkAvailabilityOfPinsInDA(pins);

        }

        return false;


    }


    private boolean checkAvailabilityOfPinsInDA(ArrayList<Integer> pins){


        retrieveListOfPinsController = new RetrieveListOfPinsController(Add_new_Shield.this);

        return retrieveListOfPinsController.checkAvailabilityOfPins(pins , Microcontroller_ID );
    }



    private String returnAvailableDigitalPin(){


        retrieveListOfPinsController = new RetrieveListOfPinsController(Add_new_Shield.this);

        return retrieveListOfPinsController.returnAvailableDigitalPin(Microcontroller_ID);
    }


    private String returnAvailableAnalogPin(){

        retrieveListOfPinsController = new RetrieveListOfPinsController(Add_new_Shield.this);


        return retrieveListOfPinsController.returnAvailableAnalogPin(Microcontroller_ID);
    }





    private void returnToPreviousLayout(){

        Intent openShieldLayoutIntent = new Intent(Add_new_Shield.this, RetrieveShieldBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openShieldLayoutIntent.putExtra("MICROCONTROLLER_ID", Microcontroller_ID);


        startActivity(openShieldLayoutIntent);


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
