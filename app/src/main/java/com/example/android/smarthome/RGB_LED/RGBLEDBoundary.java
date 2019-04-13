package com.example.android.smarthome.RGB_LED;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.smarthome.R;

public class RGBLEDBoundary extends AppCompatActivity {

    private RGBLEDController RGBLEDController;
    private Button turnOn, turnOff, changeColor;
    private EditText red, green, blue;
    private long RGBID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb_led);

        createViews();

    }

    private void createViews(){
//        Intent previousIntent = getIntent();
//
//        RGBID = previousIntent.getLongExtra("LightBulbID" , -1);


        turnOn = findViewById(R.id.turnOn_Lay_rgb_led);

        turnOff = findViewById(R.id.turnOff_Lay_rgb_led);

        red = findViewById(R.id.red_Lay_rgb_led);

        green = findViewById(R.id.green_Lay_rgb_led);
        
        blue = findViewById(R.id.blue_Lay_rgb_led);

        changeColor = findViewById(R.id.change_color_Lay_rgb_led);






        setListenerToTurnOnButton(turnOn);
        setListenerToTurnOffButton(turnOff);
        setListenerToChangeColor(changeColor);
    }

    private void setListenerToTurnOnButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGBLEDController = new RGBLEDController();
                if(RGBLEDController.turnOnRGB()){

                    Toast.makeText(RGBLEDBoundary.this , "turn on sent " , Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(RGBLEDBoundary.this , "failed to turn on " , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void setListenerToTurnOffButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RGBLEDController = new RGBLEDController();
                if(RGBLEDController.turnOffRGB()){

                    Toast.makeText(RGBLEDBoundary.this , "turn off sent " , Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(RGBLEDBoundary.this , "failed to turn off " , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void setListenerToChangeColor(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String R = red.getText().toString().trim();
                String G = green.getText().toString().trim();
                String B = blue.getText().toString().trim();
                RGBLEDController = new RGBLEDController();
                if(RGBLEDController.changeColor(R, G, B)){

                    Toast.makeText(RGBLEDBoundary.this , "change color sent " , Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(RGBLEDBoundary.this , "failed to change color " , Toast.LENGTH_LONG).show();

                }
            }


        });
    }

    
}
