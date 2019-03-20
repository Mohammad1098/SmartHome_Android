package com.example.android.smarthome.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.smarthome.Devices.RetrieveListOfDevicesBoundary;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroControllerBoundary;
import com.example.android.smarthome.R;

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


    }
}
