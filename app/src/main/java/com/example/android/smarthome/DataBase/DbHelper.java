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


        String Device_Table = "CREATE TABLE "+Schema.Device.TABLE_NAME +"("

                +Schema.Device.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.Device.NAME +" TEXT NOT NULL ,"
                +Schema.Device.TYPE +" INTEGER NOT NULL ,"
                +Schema.Device.ROOM+"TEXT NOT NULL ,"
                +Schema.Device.STATUS+"INTEGER );";



        String LightBulb_Table = "CREATE TABLE "+Schema.LightBulb.TABLE_NAME +"("

                +Schema.LightBulb.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Schema.LightBulb.NAME +" TEXT NOT NULL , "
                +Schema.LightBulb.PIN_CONNECTED +" INTEGER NOT NULL ,"
                +Schema.LightBulb.TIME +" INTEGER ,"
                +Schema.Device.ROOM+"TEXT NOT NULL ,"
                +Schema.Device.STATUS+"INTEGER );";

        db.execSQL(Device_Table);
        db.execSQL(LightBulb_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
