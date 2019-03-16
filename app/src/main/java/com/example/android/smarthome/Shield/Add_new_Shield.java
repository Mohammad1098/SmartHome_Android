package com.example.android.smarthome.Shield;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.smarthome.Adapters.Shield_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class Add_new_Shield extends AppCompatActivity {


    private Spinner shieldCategorySpinner;

    private LinearLayout LinearLayout_relay_channel;

    private EditText relay_Channel_1;

    private EditText relay_Channel_2;

    private EditText IR_pin;

    private int selectedShield=-1;

    private Button btn_AddShield;

    protected void onCreate(Bundle sa){

        super.onCreate(sa);
        setContentView(R.layout.add_new_shield);

        createView();
        attachShieldCategorySpinnerToListener();
    }


    private void createView(){

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

        relay_Channel_1 = findViewById(R.id.relay_channel_1_Lay_add_new_shield);

        relay_Channel_2 = findViewById(R.id.relay_channel_2_Lay_add_new_shield);

        IR_pin = findViewById(R.id.IR_Pin_Lay_add_new_shield);

        LinearLayout_relay_channel.setVisibility(View.GONE);

        relay_Channel_1.setVisibility(View.GONE);

        relay_Channel_2.setVisibility(View.GONE);

        IR_pin.setVisibility(View.GONE);

    }






    private void attachShieldCategorySpinnerToListener(){

        shieldCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Shield selectedShieldObj = (Shield) parent.getItemAtPosition(position);

                selectedShield = selectedShieldObj.getType(); // 0 --> Relay , 1 --> IR

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


        shieldList.add(new Shield("Relay 1 Channel" , R.drawable.relay , 1 )); // 1 for Relay 1 CHANNEL
        shieldList.add(new Shield("Relay 2 Channel" , R.drawable.relay , 2)); //2 for Relay 2 CHANNEL
        shieldList.add(new Shield("IR" , R.drawable.relay , 3)); //3 for IR

        Shield_Spinner_Adapter shieldSpinnerAdapter = new Shield_Spinner_Adapter(this , shieldList);


        return shieldSpinnerAdapter;
    }


    private void createAppropriateEditText(int type_of_Shield){


        if(type_of_Shield == 1){

            LinearLayout_relay_channel.setVisibility(View.VISIBLE);
            relay_Channel_1.setVisibility(View.VISIBLE);
            relay_Channel_2.setVisibility(View.GONE);

        }


        if(type_of_Shield == 2){
            LinearLayout_relay_channel.setVisibility(View.VISIBLE);
            relay_Channel_1.setVisibility(View.VISIBLE);
            relay_Channel_2.setVisibility(View.VISIBLE);

        }

        if(type_of_Shield == 3){
            LinearLayout_relay_channel.setVisibility(View.VISIBLE);
            relay_Channel_1.setVisibility(View.GONE);
            relay_Channel_2.setVisibility(View.GONE);
            IR_pin.setVisibility(View.VISIBLE);

        }


    }



    private void addShieldToDB(){

        if(selectedShield == -1){

            // means the user did't select any shield
            Toast.makeText(this , "Please Select Shield" , Toast.LENGTH_LONG).show();
            return;
        }


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



    private boolean checkAvailabilityOfPins(int type_of_Shield){

        // create list to contain the pins and send it to another method to check availability (checkAvailabilityOfPinsInDA)
        ArrayList<Integer> pins = new ArrayList<>();

        if(type_of_Shield == 1){

            String pin1 = relay_Channel_1.getText().toString().trim();

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));

            checkAvailabilityOfPinsInDA(pins);

        }


        if(type_of_Shield == 2){

            String pin1 = relay_Channel_1.getText().toString().trim();
            String pin2 = relay_Channel_2.getText().toString().trim();

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));
            pins.add(Integer.valueOf(pin2));

            checkAvailabilityOfPinsInDA(pins);

        }

        if(type_of_Shield == 3){


            String pin1 = IR_pin.getText().toString().trim();

            // convert String to Integer
            pins.add(Integer.valueOf(pin1));

            checkAvailabilityOfPinsInDA(pins);

        }




    }


    private boolean checkAvailabilityOfPinsInDA(ArrayList<Integer> pins){




    }
}
