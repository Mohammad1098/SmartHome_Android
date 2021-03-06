package com.example.android.smarthome.RGB_LED;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Devices.RetrieveDevicesBoundary;
import com.example.android.smarthome.IR_Devices.IR_Devices_Boundary;
import com.example.android.smarthome.R;

public class RGBLEDBoundary extends AppCompatActivity {

    private RGBLEDController RGBLEDController;
    private Button turnOn, turnOff, changeColor, wave;
    private EditText red, green, blue;
    private long RGBID;
    private long deviceId,MicroControllerID;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb_led);

        createViews();

    }

    private void createViews() {

        Intent previousIntent = getIntent();

        MicroControllerID = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);
        deviceId = previousIntent.getLongExtra("DEVICEID" , -1);
        type = previousIntent.getIntExtra("TYPE" , -1);


        turnOn = findViewById(R.id.turnOn_Lay_rgb_led);

        turnOff = findViewById(R.id.turnOff_Lay_rgb_led);

        red = findViewById(R.id.red_Lay_rgb_led);

        green = findViewById(R.id.green_Lay_rgb_led);

        blue = findViewById(R.id.blue_Lay_rgb_led);

        changeColor = findViewById(R.id.change_color_Lay_rgb_led);

        wave = findViewById(R.id.wave_Lay_rgb_led);


        setListenerToTurnOnButton(turnOn);
        setListenerToTurnOffButton(turnOff);
        setListenerToChangeColor(changeColor);
        setListenerToWaveButton(wave);
    }

    private void setListenerToTurnOnButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGBLEDController = new RGBLEDController();
                if (RGBLEDController.turnOnRGB()) {

                    Toast.makeText(RGBLEDBoundary.this, "turn on sent ", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(RGBLEDBoundary.this, "failed to turn on ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void setListenerToTurnOffButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGBLEDController = new RGBLEDController();
                if (RGBLEDController.turnOffRGB()) {

                    Toast.makeText(RGBLEDBoundary.this, "turn off sent ", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(RGBLEDBoundary.this, "failed to turn off ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void setListenerToChangeColor(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RGBLEDController = new RGBLEDController();
                String R = red.getText().toString().trim();
                String G = green.getText().toString().trim();
                String B = blue.getText().toString().trim();


                if (R.matches("") || G.matches("") || B.matches("")) {
                    Toast.makeText(RGBLEDBoundary.this, "please enter RGB values ", Toast.LENGTH_LONG).show();
                    return;
                }


                int redValue = Integer.parseInt(R);
                int greenValue = Integer.parseInt(G);
                int blueValue = Integer.parseInt(B);

                // validation , values must be from 0-255
                if (redValue >= 0 && redValue <= 255 && greenValue >= 0 && greenValue <= 255 && blueValue >= 0 && blueValue <= 255) {
                    if (RGBLEDController.changeColor(R, G, B)) {

                        Toast.makeText(RGBLEDBoundary.this, "change color sent ", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(RGBLEDBoundary.this, "failed to change color ", Toast.LENGTH_LONG).show();

                    }
                } else
                    Toast.makeText(RGBLEDBoundary.this, "values must be in (0-255) ", Toast.LENGTH_LONG).show();
            }


        });
    }

    private void setListenerToWaveButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGBLEDController = new RGBLEDController();
                if (RGBLEDController.wave()) {

                    Toast.makeText(RGBLEDBoundary.this, "wave sent ", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(RGBLEDBoundary.this, "failed to send wave ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    private void returnToPreviousLayout(){

        Intent intent = new Intent(RGBLEDBoundary.this, RetrieveDevicesBoundary.class);

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
