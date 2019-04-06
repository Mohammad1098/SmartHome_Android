package com.example.android.smarthome.LightBulb;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.android.smarthome.MQTT_Connection.MQTT_SEND_COMMANDS;
import com.example.android.smarthome.MQTT_Connection.openConnectionToMQTT_Server;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Calendar;

public class LightBulbController extends AppCompatActivity {

    private LightBulbDA lightBulbDA;
    private Activity activity;


    public LightBulbController(Activity activity){

        this.activity = activity;
        lightBulbDA = new LightBulbDA(this.activity);
    }

    public LightBulbController(){

    }


    public boolean turnOnLightBulb(){

            openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

            MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

            return commands.turnOnLightBulb(connection.returnClient() , "esp/test" , "LightBulb2ON"); // "device" LightBulb  "pin number" 2   action ON

    }



    public boolean turnOffLightBulb(){

        openConnectionToMQTT_Server connection = new openConnectionToMQTT_Server(); // to return the connection to MQTT server

        MQTT_SEND_COMMANDS commands = new MQTT_SEND_COMMANDS();

        return commands.turnOffLightBulb(connection.returnClient() , "esp/test" , "LightBulb2OFF"); // "device" LightBulb  "pin number" 2   action OFF

    }



    public boolean setTimer(long deviceId , int hours , int minutes){

        return lightBulbDA.setTimeToDA(deviceId , hours , minutes);


    }



}
