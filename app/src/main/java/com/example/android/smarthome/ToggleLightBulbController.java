package com.example.android.smarthome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ToggleLightBulbController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_light_bulb_boundary);

        Button publishButton = (Button) findViewById(R.id.button);


        // prepare the the attribute needed to set up the connection to MQTT server
        final String topic        = "esp/test";// which topic to subscribe to
        final String content      = "turnOn2LightBulb"; //Message to send  the pattern is Action/pin#/device
        final int qos             = 2; // possible values are 0 , 1 , 2  for more info https://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels/
        final String  broker       = "tcp://m15.cloudmqtt.com:11163"; // it should be tcp:// followed by MQTT server name
        final String clientId     = "JavaSample";
        final MemoryPersistence persistence = new MemoryPersistence();

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    MqttClient sampleClient = new MqttClient(broker, clientId, persistence); // establish the connection to MQTT server
                    MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setCleanSession(true);
                    connOpts.setUserName("txfqakyv");
                    connOpts.setPassword("HAZCM0Fn8NVn".toCharArray());
                    Toast.makeText(ToggleLightBulbController.this , "Connecting to broker: "+broker , Toast.LENGTH_LONG).show();
                    sampleClient.connect(connOpts);
                    System.out.println("Connected");
                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(qos);
                    sampleClient.publish(topic, message);
                    Toast.makeText(ToggleLightBulbController.this , "Message published" , Toast.LENGTH_LONG).show();
                    sampleClient.disconnect();
                    System.exit(0);
                }
                

                catch(MqttException exception) {
                    System.out.println("reason "+exception.getReasonCode());
                    System.out.println("msg "+exception.getMessage());
                    System.out.println("loc "+exception.getLocalizedMessage());
                    System.out.println("cause "+exception.getCause());
                    System.out.println("excep "+exception);
                    exception.printStackTrace();
                }


            }
        });
    }
}