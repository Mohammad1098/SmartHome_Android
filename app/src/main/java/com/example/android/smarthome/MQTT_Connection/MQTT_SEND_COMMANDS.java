package com.example.android.smarthome.MQTT_Connection;

import android.text.TextUtils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTT_SEND_COMMANDS {

    private final int qos = 2; // possible values are 0 , 1 , 2  for more info https://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels/



    public boolean sendCommand(MqttClient client, String topic , String messageContent){

        // topic which topic to subscribe to
        //messageContent is the message

        if(TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)){

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        }
        catch(MqttException exception) {

            exception.printStackTrace();
            return false;

        }


    }


/*
    public boolean turnOnLightBulb(MqttClient client, String topic , String messageContent){

        // topic which topic to subscribe to
        //messageContent is the message

        if(TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)){

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        }
        catch(MqttException exception) {

            exception.printStackTrace();
            return false;

        }


    }

    public boolean turnOffLightBulb(MqttClient client, String topic , String messageContent){

        // topic which topic to subscribe to
        //messageContent is the message

        if(TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)){

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        }
        catch(MqttException exception) {

            exception.printStackTrace();
            return false;

        }


    }

    public boolean turnOnRGBLED(MqttClient client, String topic, String messageContent){

        if(TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)){

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        }
        catch(MqttException exception) {

            exception.printStackTrace();
            return false;

        }
    }

    public boolean turnOffRGBLED(MqttClient client, String topic, String messageContent){

        if(TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)){

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        }
        catch(MqttException exception) {

            exception.printStackTrace();
            return false;

        }
    }

    public boolean changeColor(MqttClient client, String topic, String messageContent){
        if (TextUtils.isEmpty(topic) || TextUtils.isEmpty(messageContent)) {

            return false;

        }

        MqttMessage message = new MqttMessage(messageContent.getBytes());
        message.setQos(qos);
        try {
            client.publish(topic, message);
            return true;
        } catch (MqttException exception) {

            exception.printStackTrace();
            return false;

        }
    }
    */
}
