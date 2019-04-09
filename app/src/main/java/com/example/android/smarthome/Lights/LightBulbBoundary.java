package com.example.android.smarthome.Lights;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.smarthome.R;

import java.util.Calendar;


public class LightBulbBoundary extends AppCompatActivity {

    // global variable of class LightBulbController to access it in each method
    private LightBulbController lightBulbController;
    private Button turnOn , turnOff , setTime;
    private long LightBulbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightbulb);

        createViews();


    }


    private void createViews(){

        Intent previousIntent = getIntent();

        LightBulbId = previousIntent.getLongExtra("LightBulbID" , -1);


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

                        lightBulbController.setTimer(LightBulbId , hourOfDay , minutes);

                    }
                }, currentHour, currentMinute, false);

            }
        });


    }



}