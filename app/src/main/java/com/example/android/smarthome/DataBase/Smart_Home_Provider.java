package com.example.android.smarthome.DataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;



public class Smart_Home_Provider extends  ContentProvider {

    //instance of class DbHelper to use get Writable or Readable
    private DbHelper mDataBase;



    //this attribute use to math the incoming Uri , so we define the possible Uri that will come from the activities
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        //## for Device Table


        sUriMatcher.addURI(Schema.Device.CONTENT_AUTHORITY, Schema.Device.PATH , 1);

        // this for a specific device
        sUriMatcher.addURI(Schema.Device.CONTENT_AUTHORITY, Schema.Device.PATH+"/#" , 2);



        //## for LightBulb Table

        sUriMatcher.addURI(Schema.LightBulb.CONTENT_AUTHORITY, Schema.LightBulb.PATH , 3);

        // this for a specific device
        sUriMatcher.addURI(Schema.LightBulb.CONTENT_AUTHORITY, Schema.LightBulb.PATH+"/#" , 4);


        //## for Operation Table

        sUriMatcher.addURI(Schema.Operation.CONTENT_AUTHORITY, Schema.Operation.PATH , 5);


        //## for DeviceCategory Table

        sUriMatcher.addURI(Schema.DeviceCategory.CONTENT_AUTHORITY, Schema.DeviceCategory.PATH , 6);


    }



    @Override
    public boolean onCreate() {
        mDataBase = new DbHelper(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder ) {


        SQLiteDatabase db = mDataBase.getReadableDatabase();

        int match = sUriMatcher.match(uri);

        Cursor cursor;

        switch (match){

            //this is means that we want to retrieve the whole data exist in Device Table
            case 1:
                cursor = db.query(Schema.Device.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                break;

            //this is means that we want to retrieve the specific data exist in Device Table
            case 2:

                selection = Schema.Device.ID +"=?"; // same to ID=?      ? we will get the number of row in next line
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(Schema.Device.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder );
                break;



            //this is means that we want to retrieve the whole data exist in LightBulb Table
            case 3:

                cursor = db.query(Schema.LightBulb.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder );
                break;

            //this is means that we want to retrieve the specific data exist in LightBulb Table
            case 4:

                selection = Schema.Device.ID +"=?"; // same to ID=?      ? we will get the number of row in next line
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(Schema.LightBulb.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder );
                break;

            case 5:

                Log.e("Provider" ,selection );

                cursor = db.query(Schema.Operation.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder );
                break;

            case 6:

                cursor = db.query(Schema.DeviceCategory.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder );
                break;

            default:
                throw  new IllegalArgumentException("Error "+ uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver() , uri);

        return cursor;



    }

    @Override
    public Uri insert( Uri uri,  ContentValues values) {


        int match = sUriMatcher.match(uri);


        switch (match){

            case 1:
                // Helper method
                return insertRecord(uri , values , match);


            case 5:
                // Helper method
                return insertRecord(uri , values , match);

            case 6:
                // Helper method
                return insertRecord(uri , values , match);

            default:
                throw new IllegalArgumentException("Error " + uri);

        }
    }


    private Uri insertRecord(Uri uri , ContentValues contentValues , int match){


        SQLiteDatabase db = mDataBase.getWritableDatabase();

        long id=-1;
        switch (match){


            case 1:
            id = db.insert(Schema.Device.TABLE_NAME, null, contentValues);
            break;

            case 5:
                id = db.insert(Schema.Operation.TABLE_NAME, null, contentValues);
            break;

            case 6:
                id = db.insert(Schema.DeviceCategory.TABLE_NAME, null, contentValues);
                break;

            default:
                return null;


        }



        return ContentUris.withAppendedId(uri , id);


    }


    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {

        //TODO

        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {

        //TODO

        return 0;
    }

    @Override
    public String getType( Uri uri) {

        //TODO

        return null;
    }
}
