package com.example.android.smarthome.RGB_LED;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.example.android.smarthome.MQTT_Connection.MQTT_SEND_COMMANDS;
import com.example.android.smarthome.MQTT_Connection.openConnectionToMQTT_Server;

public class RGBLEDController extends AppCompatActivity {

    private RGBLEDDA RGBLEDDA;
    private Activity activity;

    public RGBLEDController (Activity activity){
        this.activity = activity;
        RGBLEDDA = new RGBLEDDA(this.activity);
    }

    public RGBLEDController(){
    }

    public boolean turnOnRGB(){
        openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.sendCommand(connection.returnClient() , "SMARTHOME" , "LEDON"); // "device" RGB  "pin number" 2   action ON

    }

    public boolean turnOffRGB(){
        openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.sendCommand(connection.returnClient() , "SMARTHOME" , "LEDOFF"); // "device" RGB  "pin number" 2   action ON

    }

    public boolean changeColor(String red, String green, String blue){

//        covert values to 3 digits
        if(red.length() == 1)
            red = "00"+red;
        else if(red.length() == 2)
            red = "0"+red;
        
        if(green.length() == 1)
            green = "00"+green;
        else if(green.length() == 2)
            green = "0"+green;

        if(blue.length() == 1)
            blue = "00"+blue;
        else if(blue.length() == 2)
            blue = "0"+blue;

        openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.sendCommand(connection.returnClient() , "SMARTHOME" , "R"+red+"G"+green+"B"+blue); // "device" RGB  "pin number" 2   action changeColor

    }

}
