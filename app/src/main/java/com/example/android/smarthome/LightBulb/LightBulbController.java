package com.example.android.smarthome.LightBulb;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Calendar;

public class LightBulbController extends AppCompatActivity {

        // prepare the the attribute needed to set up the connection to MQTT server
        private final String topic        = "esp/test";// which topic to subscribe to
        private final int qos             = 2; // possible values are 0 , 1 , 2  for more info https://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels/
        private final String  broker       = "tcp://m15.cloudmqtt.com:11163"; // it should be tcp:// followed by MQTT server name
        private final String clientId     = "JavaSample";
        private final MemoryPersistence persistence = new MemoryPersistence();
        private MqttClient sampleClient;




        public LightBulbController(){



            try {
                sampleClient = new MqttClient(broker, clientId, persistence); // establish the connection to MQTT server
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setUserName("txfqakyv");
                connOpts.setPassword("HAZCM0Fn8NVn".toCharArray());
                //Toast.makeText(LightBulbBoundary.this, "Connecting to broker: " + broker, Toast.LENGTH_LONG).show();
                sampleClient.connect(connOpts);

            }

            catch(MqttException exception) {

                exception.printStackTrace();
            }

        }



        public boolean toggleLightBulb(){

            String content = "toggle2";
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            try {
                sampleClient.publish(topic, message);
                Log.v("dfsdf" , "sdfsdf");

                return true;
            }
            catch(MqttException exception) {

                exception.printStackTrace();
                return false;

            }


        }



    public TimePickerDialog setTimer(Context context){




        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


            }
        }, currentHour, currentMinute, false);

        return timePickerDialog;

    }



}
