package com.example.android.smarthome.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME="SmartHome.db";

    private static final int DATABASE_VERSION =1;

    public DbHelper(Context context){

        super(context , DATABASE_NAME , null , DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String DeviceCategory_Table = "CREATE TABLE "+Schema.DeviceCategory.TABLE_NAME +"("

                +Schema.DeviceCategory.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.DeviceCategory.NAME +" TEXT NOT NULL ,"
                +Schema.DeviceCategory.TYPE +" INTEGER NOT NULL );";



        String Device_Table = "CREATE TABLE "+Schema.Device.TABLE_NAME +"("

                +Schema.Device.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.Device.NAME +" TEXT NOT NULL ,"
                +Schema.Device.TYPE +" INTEGER NOT NULL ,"
                +Schema.Device.ROOM+" TEXT NOT NULL ,"
                +Schema.Device.PIN+" INTEGER NOT NULL ,"
                +Schema.Device.STATUS+" INTEGER );";



        String LightBulb_Table = "CREATE TABLE "+Schema.LightBulb.TABLE_NAME +"("

                +Schema.LightBulb.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.LightBulb.TIME +" INTEGER ,"
                +Schema.LightBulb.DEVICE_ID+" INTEGER ,"
                +"FOREIGN KEY ("+ Schema.LightBulb.DEVICE_ID+") REFERENCES "+Schema.Device.TABLE_NAME+"("+Schema.Device.ID+"));";



        String Operation_Table ="CREATE TABLE "+Schema.Operation.TABLE_NAME +"("

                +Schema.Operation.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.Operation.NAME+" TEXT NOT NULL , "
                +Schema.Operation.IMPLEMENTATION+" TEXT NOT NULL , "
                +Schema.Operation.DEVICE_ID+" INTEGER ,"
                +"FOREIGN KEY ("+ Schema.Operation.DEVICE_ID+") REFERENCES "+Schema.Device.TABLE_NAME+"("+Schema.Device.ID+"));";



        db.execSQL(DeviceCategory_Table);
        db.execSQL(Device_Table);
        db.execSQL(LightBulb_Table);
        db.execSQL(Operation_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
