package com.example.android.smarthome.DataBase;

import android.net.Uri;
import android.provider.BaseColumns;
import java.net.URI;


public final class Schema   {


    //Each Class represent Table


    // MicroController table

    public static abstract class MicroController implements BaseColumns{

        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        public static final String PATH = "MicroController";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);

        public static final String TABLE_NAME = "MicroController";

        public static final String ID = BaseColumns._ID;

        public static final String NAME ="NAME";

        public static final String TYPE ="TYPE";

        public static final String ROOM ="ROOM";


    }


    //Shield category table


    public static abstract class Shield implements BaseColumns{

        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        public static final String PATH = "Shield";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);

        public static final String TABLE_NAME = "Shield";

        public static final String ID =BaseColumns._ID;

        public static final String NAME="NAME";

        public static final String AVAILABILITY = "AVAILABILITY";

        public static final String MICROCONTROLLER_ID ="MICROCONTROLLER_ID";

        public static final String TYPE ="TYPE";



    }


    // Pins Table

    public static abstract class Pin implements BaseColumns{


        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        public static final String PATH = "Pin";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);

        public static final String TABLE_NAME = "Pin";

        public static final String ID = BaseColumns._ID;

        public static final String PIN_NUMBER = "PINNUMBER";

        public static final String DEVICE_ID = "DEVICE_ID";

        public static final String MICROCONTROLLER_ID = "MICROCONTROLLERID";

        public static final String SHIELD_ID = "SHIELD_ID";

        public static final String AVAILABILITY = "AVAILABILITY";

        public static final String TYPE = "TYPE";



    }









    //Device category table


    public static abstract class DeviceCategory implements BaseColumns{

        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        public static final String PATH = "DeviceCategory";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);


        // these attributes used to represent the name of column so they are String
        public static final String TABLE_NAME = "DeviceCategory";

        public static final String ID = BaseColumns._ID;

        public static final String NAME ="NAME";

        public static final String TYPE ="TYPE";



    }




    //Device Table
    public static abstract class Device implements BaseColumns{



        // we need this later in class Provider for the UriMatcher
        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        // we need this later in class Provider for the UriMatcher
        public static final String PATH = "Device";

        //this Uri will use later to add,delete,update etc ..
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);





        // these attributes used to represent the name of column so they are String
        public static final String TABLE_NAME = "Device";

        public static final String ID = BaseColumns._ID;

        public static final String NAME ="NAME";

        public static final String TYPE ="TYPE";

        public static final String ROOM ="ROOM";

        public static final String MICROCONTROLLER_ID ="MICROCONTROLLER_ID";



    }


    //LightBulb Table
    public static abstract class LightBulb implements BaseColumns{

        // we need this later in class Provider for the UriMatcher
        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        // we need this later in class Provider for the UriMatcher
        public static final String PATH = "LightBulb";

        //this Uri will use later to add,delete,update etc ..
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);





        // these attributes used to represent the name of column so they are String
        public static final String TABLE_NAME = "LightBulb";

        public static final String ID =BaseColumns._ID;

        public static final String DEVICE_ID = "DEVICEID";

        public static final String TIME ="TIME";


    }




    public static abstract class Operation implements BaseColumns{

        public static final String CONTENT_AUTHORITY ="com.example.android.SmartHome";

        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

        public static final String PATH = "Operation";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);

        public static final String TABLE_NAME = "Operation";

        public static final String ID = BaseColumns._ID;

        public static final String DEVICE_ID = "DEVICEID";

        public static final String NAME = "NAME";

        public static final String FREQUENCY = "FREQUENCY";

        public static final String IMPLEMENTATION = "IMPLEMENTATION";

    }


}
