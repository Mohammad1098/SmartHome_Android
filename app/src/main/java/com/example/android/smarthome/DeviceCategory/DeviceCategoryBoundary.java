package com.example.android.smarthome.DeviceCategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.smarthome.Devices.RetrieveMicroControllerDevicesBoundary;
import com.example.android.smarthome.IR_Devices.IR_Devices_Boundary;
import com.example.android.smarthome.Lights.LightBulbBoundary;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.RetrieveShieldBoundary;


public class DeviceCategoryBoundary extends AppCompatActivity {

    private long MicrocontrollerId;
    private Button devices , shields , lightbulb , tv , receiver , ac;

    @Override
    public void onCreate(Bundle sa){

        super.onCreate(sa);
        setContentView(R.layout.device_category);

        createViews();
    }

    private void createViews(){

        Intent previousIntent = getIntent();

        MicrocontrollerId = previousIntent.getLongExtra("MicrocontrollerId" , -1);


        devices = findViewById(R.id.DevicesImage_Lay_device_category);
        shields = findViewById(R.id.ShieldsImage_Lay_device_category);
        lightbulb = findViewById(R.id.LightImage_Lay_device_category);
        tv = findViewById(R.id.TVImage_Lay_device_category);
        receiver = findViewById(R.id.ReceiverImage_Lay_device_category);
        ac = findViewById(R.id.ACImage_Lay_device_category);


        setListenerToDevicesButton(devices);
        setListenerToShieldsButton(shields);
        setListenerToLightsButton(lightbulb);
        setListenerToTVButton(tv);
        setListenerToReceiverButton(receiver);
        setListenerToACButton(ac);


    }

    private void setListenerToDevicesButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent devicesIntent = new Intent(DeviceCategoryBoundary.this , RetrieveMicroControllerDevicesBoundary.class);

               devicesIntent.putExtra("MicrocontrollerId" , MicrocontrollerId);

               startActivity(devicesIntent);



            }
        });


    }

    private void setListenerToShieldsButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shieldsIntent = new Intent(DeviceCategoryBoundary.this , RetrieveShieldBoundary.class);

                shieldsIntent.putExtra("MicrocontrollerId" , MicrocontrollerId);

                startActivity(shieldsIntent);

            }
        });


    }

    private void setListenerToLightsButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lightsIntent = new Intent(DeviceCategoryBoundary.this , LightBulbBoundary.class);

                lightsIntent.putExtra("MicrocontrollerId" , MicrocontrollerId);

                startActivity(lightsIntent);

            }
        });


    }


    private void setListenerToTVButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TV_Intent = new Intent(DeviceCategoryBoundary.this , IR_Devices_Boundary.class);

                TV_Intent.putExtra("MicrocontrollerId" , MicrocontrollerId);
                TV_Intent.putExtra("TYPE" , 2); // 2 means tv



                startActivity(TV_Intent);

            }
        });


    }


    private void setListenerToReceiverButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Receiver_Intent = new Intent(DeviceCategoryBoundary.this , IR_Devices_Boundary.class);

                Receiver_Intent.putExtra("MicrocontrollerId" , MicrocontrollerId);
                Receiver_Intent.putExtra("TYPE" , 3); // 3 means Receiver



                startActivity(Receiver_Intent);
            }
        });


    }



    private void setListenerToACButton(Button button ){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent AC_Intent = new Intent(DeviceCategoryBoundary.this , IR_Devices_Boundary.class);

                AC_Intent.putExtra("MicrocontrollerId" , MicrocontrollerId);
                AC_Intent.putExtra("TYPE" , 4); // 4 means ac



                startActivity(AC_Intent);

            }
        });


    }

}
