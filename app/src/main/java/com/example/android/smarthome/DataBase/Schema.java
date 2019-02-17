package com.example.android.smarthome.DataBase;

import android.net.Uri;
import android.provider.BaseColumns;
import java.net.URI;


public final class Schema   {


    //Each Class represent Table




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

        public static final String STATUS ="STATUS";



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

        public static final String NAME ="NAME";

        public static final String PIN_CONNECTED ="PINCONNECTED";

        public static final String TIME ="TIME";

        public static final String ROOM ="ROOM";

        public static final String STATUS ="STATUS";






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

        public static final String IMPLEMENTATION = "IMPLEMENTATION";

    }


}
