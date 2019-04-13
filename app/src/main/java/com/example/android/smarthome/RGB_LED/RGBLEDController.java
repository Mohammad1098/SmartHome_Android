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

        return commands.turnOnRGBLED(connection.returnClient() , "SMARTHOME" , "RGB2ON"); // "device" RGB  "pin number" 2   action ON

    }

    public boolean turnOffRGB(){
        openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.turnOffRGBLED(connection.returnClient() , "SMARTHOME" , "RGB2OFF"); // "device" RGB  "pin number" 2   action ON

    }

    public boolean changeColor(String red, String green, String blue){
         openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.changeColor(connection.returnClient() , "SMARTHOME" , "RGB2R"+red+"G"+green+"B"+blue); // "device" RGB  "pin number" 2   action changeColor

    }

}
