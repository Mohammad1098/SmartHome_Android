package com.example.android.smarthome.MicroController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.smarthome.Devices.RetrieveMicroControllerDevicesBoundary;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.RetrieveShieldBoundary;

public class RetrieveSpecificMicroControllerBoundary extends AppCompatActivity {


    private long MicroControllerID;
    private RetrieveSpecificMicroControllerController retrieveSpecificMicroControllerController;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_category);
        createViews();

    }

    private void createViews(){


        Intent previousIntent = getIntent();

        MicroControllerID = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);


        retrieveSpecificMicroControllerController = new RetrieveSpecificMicroControllerController(RetrieveSpecificMicroControllerBoundary.this ,MicroControllerID ); // this line just for testing

        //retrieveSpecificMicroControllerController.TEST_displaySpecificMicroControllerTable();// this line just for testing

        Button MicroControllerDevicesImage = findViewById(R.id.DevicesImage_Lay_specific_micro_controller);

        Button MicroControllerShieldsImage = findViewById(R.id.ShieldsImage_Lay_specific_micro_controller);


        MicroControllerDevicesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent MicroControllerDeviceIntent = new Intent(RetrieveSpecificMicroControllerBoundary.this , RetrieveMicroControllerDevicesBoundary.class);

                MicroControllerDeviceIntent.putExtra("MICROCONTROLLER_ID" , MicroControllerID);

                startActivity(MicroControllerDeviceIntent);

            }
        });


        MicroControllerShieldsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent MicroControllerShieldIntent = new Intent(RetrieveSpecificMicroControllerBoundary.this , RetrieveShieldBoundary.class);

                MicroControllerShieldIntent.putExtra("MICROCONTROLLER_ID" , MicroControllerID);

                startActivity(MicroControllerShieldIntent);


            }
        });

    }


    private void returnToPreviousLayout(){

        Intent openRetrieveMicroControllerLayoutIntent = new Intent(RetrieveSpecificMicroControllerBoundary.this, RetrieveListOfMicroControllerBoundary.class);

        startActivity(openRetrieveMicroControllerLayoutIntent);


    }


    @Override
    public void onBackPressed() {

        returnToPreviousLayout();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            returnToPreviousLayout();
            return true;

    }


}
