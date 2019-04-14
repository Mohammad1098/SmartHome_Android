package com.example.android.smarthome.Pins;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.MicroController.MicroController;
import com.example.android.smarthome.MicroController.RetrieveListOfMicroController_Controller;
import com.example.android.smarthome.Shield.Shield;

import java.util.ArrayList;

public class RetrieveListOfPinsDA {

    private Activity activity;
    private long microControllerId;

    public RetrieveListOfPinsDA(Activity activity , long MicroController_ID){

        this.activity = activity;
        microControllerId=MicroController_ID;


    }

    public boolean checkAvailabilityOfPins(ArrayList<Integer> pins ){

        boolean availability;

        RetrieveListOfMicroController_Controller microController_controller = new RetrieveListOfMicroController_Controller(this.activity);



        for (int i=0 ; i<pins.size() ; i++){



            if(!checkArduinoPins(pins.get(i) , microController_controller.returnMicroControllerType(this.microControllerId))){

                Log.e("Pins DA " , " PIN > 13 OR PIN < 0 ");


                return false;
            }

            availability =checkIndividualPin(pins.get(i));

            if(availability ==false){

                Log.e("Pins DA " , " PIN ALREADY EXISIT ");

                return false;
            }

        }

        return true;


    }


    private boolean checkIndividualPin(Integer pin){



        String selection = "MICROCONTROLLERID="+this.microControllerId;

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);

        if(cursor == null){

            Log.e("Pins DA " , " NULL ");


            return false;
        }

        cursor.moveToFirst();

        for (int i=0 ; i<cursor.getCount() ; i++){


            if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)) == pin){

                    if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.AVAILABILITY)) == 0){

                        Log.e("Pin da " , " "+pin);

                        return false;
                    }

                }


                cursor.moveToNext();


        }

        return true;

    }


    private boolean checkArduinoPins(int pin , int microControllerType){

        //Arduino Uno
        if (microControllerType == 0) {

            if (pin > 13 || pin < 0) {

                return false;
                }
        }

        //Arduino Nano
        if (microControllerType == 1) {

            if (pin > 12 || pin < 0) {

                return false;
            }
        }

                return true;
    }

    public String returnAvailableDigitalPin(){


        String selection = "MICROCONTROLLERID="+this.microControllerId +" AND TYPE= 0";

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);



        if(cursor == null){


            return null;
        }


        String AvailableDigitalPin = "";

        cursor.moveToFirst();

        for (int i=0 ; i<cursor.getCount() ; i++){



            if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.AVAILABILITY)) == 1 ){  // 0 type --> Digital  , 1 AVAILABILITY --> it's free to use


               AvailableDigitalPin +=String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)));

               if (i != cursor.getCount()-1 ){

                   AvailableDigitalPin +=" ,";

               }
           }

            cursor.moveToNext();


        }

        Log.e("DD" ,  "text is " +AvailableDigitalPin);


        return AvailableDigitalPin;

    }




    public String returnAvailableAnalogPin(){  // it is same to returnAvailableDigitalPin but here we are looking for cursor.getInt(cursor.getColumnIndex(Schema.Pin.TYPE)) == 1

        String selection = "MICROCONTROLLERID="+this.microControllerId +" AND TYPE=1";

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);

        if(cursor == null){

            return null;
        }

        String AvailableAnalogPin = "";

        cursor.moveToFirst();

        for (int i=0 ; i<cursor.getCount() ; i++){



            if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.AVAILABILITY)) == 1 ){  //  1 type --> Analog  , 1 AVAILABILITY --> it's free to use

                AvailableAnalogPin +=String.valueOf(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)));

                if (i != cursor.getCount()-1){

                    AvailableAnalogPin +=" ,";

                }
            }

            cursor.moveToNext();


        }

        return AvailableAnalogPin;

    }


    public int returnShieldPin(long MicroControllerID ,long ShieldID){


        String selection = "MICROCONTROLLERID="+MicroControllerID +" AND SHIELD_ID"+ShieldID;

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);


        cursor.moveToFirst();

        if(cursor == null){

            return -1;
        }


        else {

            return cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER));
        }



    }


    public boolean check_RGB_LED_Pins(int pin){

        String selection = "MICROCONTROLLERID="+this.microControllerId +" AND AVAILABILITY=1 AND TYPE=0" ;

        Cursor cursor = activity.getApplicationContext().getContentResolver().query(Schema.Pin.CONTENT_URI , null , selection , null , null);

        if(cursor == null){


            return false;
        }

        cursor.moveToFirst();

        for (int i=0 ; i<cursor.getCount() ; i++){



            if(cursor.getInt(cursor.getColumnIndex(Schema.Pin.PIN_NUMBER)) == pin){

                return Arduino_Uno_PWM_Pins(pin);

            }

            cursor.moveToNext();


        }

        return false;

    }

    private boolean Arduino_Uno_PWM_Pins(int pin){  // pwm pin , it's pin that we can use analogWrite(pin , value ) method   value 0-255

        if(pin == 3 || pin == 5 ||  pin == 6 || pin == 9  || pin == 10 || pin == 11){

            return true;
        }

        else return false;

    }

}
