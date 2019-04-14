package com.example.android.smarthome.DeviceCategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.android.smarthome.Devices.RetrieveDevicesBoundary;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroControllerBoundary;
import com.example.android.smarthome.R;
import com.example.android.smarthome.RGB_LED.RGBLEDBoundary;
import com.example.android.smarthome.Shield.RetrieveShieldBoundary;


public class DeviceCategoryBoundary extends AppCompatActivity {

    private long MicrocontrollerId;
    private Button devices , shields , lightbulb ,RGBLED, tv , receiver , ac;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_category);
        createViews();
    }

    private void createViews(){

        setTitle("Categories");

        Intent previousIntent = getIntent();


        MicrocontrollerId = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);

        Log.e("Device cat" , "Micro id"+ MicrocontrollerId);

        devices = findViewById(R.id.DevicesImage_Lay_device_category);
        shields = findViewById(R.id.ShieldsImage_Lay_device_category);
        lightbulb = findViewById(R.id.LightImage_Lay_device_category);
        RGBLED= findViewById(R.id.RGBLEDImage_Lay_device_category);
        tv = findViewById(R.id.TVImage_Lay_device_category);
        receiver = findViewById(R.id.ReceiverImage_Lay_device_category);
        ac = findViewById(R.id.ACImage_Lay_device_category);


        setListenerToDevicesButton(devices);
        setListenerToShieldsButton(shields);
        setListenerToLightsButton(lightbulb);
        setListenerToRGBLEDButton(RGBLED);
        setListenerToTVButton(tv);
        setListenerToReceiverButton(receiver);
        setListenerToACButton(ac);


    }

    private void setListenerToDevicesButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent devicesIntent = new Intent(DeviceCategoryBoundary.this , RetrieveDevicesBoundary.class);

               devicesIntent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
               devicesIntent.putExtra("TYPE" , 100);

               startActivity(devicesIntent);



            }
        });


    }

    private void setListenerToShieldsButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shieldsIntent = new Intent(DeviceCategoryBoundary.this , RetrieveShieldBoundary.class);

                shieldsIntent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                shieldsIntent.putExtra("TYPE" , 101);

                startActivity(shieldsIntent);

            }
        });


    }

    private void setListenerToLightsButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lightsIntent = new Intent(DeviceCategoryBoundary.this , RetrieveDevicesBoundary.class);

                lightsIntent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                lightsIntent.putExtra("TYPE" , 0);

                startActivity(lightsIntent);

            }
        });


    }


    private void setListenerToRGBLEDButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lightsIntent = new Intent(DeviceCategoryBoundary.this , RGBLEDBoundary.class);

                lightsIntent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                lightsIntent.putExtra("TYPE" , 1);

                startActivity(lightsIntent);

            }
        });


    }


    private void setListenerToTVButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TV_Intent = new Intent(DeviceCategoryBoundary.this , RetrieveDevicesBoundary.class);

                TV_Intent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                TV_Intent.putExtra("TYPE" , 2); // 2 means tv



                startActivity(TV_Intent);

            }
        });


    }


    private void setListenerToReceiverButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Receiver_Intent = new Intent(DeviceCategoryBoundary.this , RetrieveDevicesBoundary.class);

                Receiver_Intent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                Receiver_Intent.putExtra("TYPE" , 3); // 3 means Receiver



                startActivity(Receiver_Intent);
            }
        });


    }



    private void setListenerToACButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent AC_Intent = new Intent(DeviceCategoryBoundary.this , RetrieveDevicesBoundary.class);

                AC_Intent.putExtra("MICROCONTROLLER_ID" , MicrocontrollerId);
                AC_Intent.putExtra("TYPE" , 4); // 4 means ac



                startActivity(AC_Intent);

            }
        });


    }


    private void returnToPreviousLayout(){

        Intent Intent = new Intent(DeviceCategoryBoundary.this, RetrieveListOfMicroControllerBoundary.class);

        startActivity(Intent);


    }


    @Override
    public void onBackPressed() {

        returnToPreviousLayout();
        finish();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        returnToPreviousLayout();
        finish();
        return true;

    }

}
