package com.example.android.smarthome.Lights;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.DeviceCategory.DeviceCategoryBoundary;
import com.example.android.smarthome.Devices.RetrieveDevicesBoundary;
import com.example.android.smarthome.R;

import java.util.ArrayList;
import java.util.Calendar;


public class LightBulbBoundary extends AppCompatActivity {

    // global variable of class LightBulbController to access it in each method
    private LightBulbController lightBulbController;
    private Button turnOn , turnOff , setTime;
    private long deviceId,MicroControllerID;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightbulb);

        createViews();


    }


    private void createViews(){



        Intent previousIntent = getIntent();

        MicroControllerID = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);
        deviceId = previousIntent.getLongExtra("DEVICEID" , -1);
        type = previousIntent.getIntExtra("TYPE" , -1);


        turnOn = findViewById(R.id.turnOn_Lay_light_bulb);

        turnOff = findViewById(R.id.turnOff_Lay_light_bulb);

        setTime = findViewById(R.id.set_Time_Lay_light_bulb);

        setListenerToTurnOnButton(turnOn);
        setListenerToTurnOffButton(turnOff);
        setListenerToSetTimeButton(setTime);

    }



    private void setListenerToTurnOnButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lightBulbController = new LightBulbController();
                if(lightBulbController.turnOnLightBulb()){

                    Toast.makeText(LightBulbBoundary.this , "turn on sent " , Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(LightBulbBoundary.this , "failed to turn on " , Toast.LENGTH_LONG).show();

                }

            }
        });


    }


    private void setListenerToTurnOffButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lightBulbController = new LightBulbController();
                if(lightBulbController.turnOffLightBulb()){

                    Toast.makeText(LightBulbBoundary.this , "turn Off sent " , Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(LightBulbBoundary.this , "failed to turn Off " , Toast.LENGTH_LONG).show();

                }

            }
        });



    }



    private void setListenerToSetTimeButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                final LightBulbController lightBulbController = new LightBulbController(LightBulbBoundary.this);


                TimePickerDialog timePickerDialog = new TimePickerDialog(LightBulbBoundary.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        lightBulbController.setTimer(deviceId , hourOfDay , minutes);
                        Toast.makeText(LightBulbBoundary.this , "Time "+hourOfDay+":"+minutes , Toast.LENGTH_LONG).show();

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });


    }

    private void returnToPreviousLayout(){

        Intent intent = new Intent(LightBulbBoundary.this, RetrieveDevicesBoundary.class);

        intent.putExtra("MICROCONTROLLER_ID"  , MicroControllerID);
        intent.putExtra("DEVICEID"  , deviceId);
        intent.putExtra("TYPE"  , type);

        startActivity(intent);


    }


    @Override
    public void onBackPressed() {

        returnToPreviousLayout();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu , menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.delete :
                DeleteDevice();
                return true;
        }

        returnToPreviousLayout();
        return true;

    }


    private void DeleteDevice(){

        Log.e("dele" , "deeelete ");

        String selection = "MICROCONTROLLER_ID="+MicroControllerID +" AND _id="+deviceId;
        getContentResolver().delete(Schema.Device.CONTENT_URI ,selection , null );
        returnToPreviousLayout();

    }


}