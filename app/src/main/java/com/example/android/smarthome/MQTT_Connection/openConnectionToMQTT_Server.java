package com.example.android.smarthome.MQTT_Connection;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class openConnectionToMQTT_Server {

    MqttClient sampleClient;


    public openConnectionToMQTT_Server(){


        // prepare the the attribute needed to set up the connection to MQTT server

         final String  broker       = "tcp://m15.cloudmqtt.com:11163"; // it should be tcp:// followed by MQTT server name
         final String clientId     = "JavaSample";
         final MemoryPersistence persistence = new MemoryPersistence();

        try {
            sampleClient = new MqttClient(broker, clientId, persistence); // establish the connection to MQTT server
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName("txfqakyv");
            connOpts.setPassword("HAZCM0Fn8NVn".toCharArray());
            sampleClient.connect(connOpts);

        }

        catch(MqttException exception) {

            exception.printStackTrace();
        }

    }



    public MqttClient returnClient(){

        return this.sampleClient;

    }

}
