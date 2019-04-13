package com.example.android.smarthome.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.smarthome.Devices.RetrieveListOfDevicesBoundary;
import com.example.android.smarthome.Devices.RetrieveListOfDevicesDA;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroControllerBoundary;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroControllerDA;
import com.example.android.smarthome.Pins.RetrieveListOfPinsDA;
import com.example.android.smarthome.R;
import com.example.android.smarthome.RGB_LED.RGBLEDBoundary;
import com.example.android.smarthome.Shield.RetrieveShieldDA;

public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        setTitle("Smart Home");
        createViews();
    }


    private void createViews(){

        Button openDevices = findViewById(R.id.openDevices);

        Button openMicroControllers = findViewById(R.id.openMicroController);

        Button openRGBLed = findViewById(R.id.rgb_led_home_screen);

        openRGBLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRGBIntent = new Intent(HomeScreen.this, RGBLEDBoundary.class);
                startActivity(openRGBIntent);
            }
        });


        openDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openDeviceIntent = new Intent(HomeScreen.this , RetrieveListOfDevicesBoundary.class);


                startActivity(openDeviceIntent);


            }
        });


        openMicroControllers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openMicroControllerIntent = new Intent(HomeScreen.this , RetrieveListOfMicroControllerBoundary.class);


                startActivity(openMicroControllerIntent);

            }
        });



        //for testing
        RetrieveListOfDevicesDA retrieveListOfDevicesDA = new RetrieveListOfDevicesDA(HomeScreen.this);
        //retrieveListOfDevicesDA.TEST_displayDeviceTable();


        RetrieveListOfMicroControllerDA retrieveListOfMicroControllerDA = new RetrieveListOfMicroControllerDA(HomeScreen.this);
        //retrieveListOfMicroControllerDA.TEST_displayMicroControllerTable();




    }
}
