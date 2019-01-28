package com.example.android.smarthome.LightBulb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.smarthome.R;


public class LightBulbBoundary extends AppCompatActivity {

    // global variable of class LightBulbController to access it in each method
     LightBulbController lightBulbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_bulb_list_item);

        //buttons to set listener when it is clicked
        Button toggleLightBulbButton = (Button) findViewById(R.id.toggle_light_Lay_light_bulb_list_item);
        Button setTimeButton = (Button) findViewById(R.id.set_time_Lay_light_bulb_list_item);


        //Create instance to class LightBulbController() to use it's functions
         lightBulbController = new LightBulbController();



        toggleLightBulbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleLightBulb();

            }
        });



        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer();

            }
        });


    }




    //Requirement #2
    public void toggleLightBulb(){

        lightBulbController.toggleLightBulb();

    }


    //Requirement #3
    public void setTimer(){

         lightBulbController.setTimer(LightBulbBoundary.this).show();


    }


    public void displayMessage(){



    }







}