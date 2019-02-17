package com.example.android.smarthome.Devices;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.R;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;


public class RetrieveListOfDevicesBoundary extends AppCompatActivity {


    private ListView deviceListView;
    private RetrieveListOfDevicesController retrieveListOfDevicesController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_devices);


        // Adding dummy DEVICE category

            ContentValues content = new ContentValues();
            content.put(Schema.DeviceCategory.NAME, "Light Bulb");
            content.put(Schema.DeviceCategory.TYPE, 1);
            getContentResolver().insert(Schema.DeviceCategory.CONTENT_URI, content);


        // Adding dummy DEVICE

        ContentValues content2 = new ContentValues();
        content2.put(Schema.Device.NAME, "Specific Light bulb");
        content2.put(Schema.Device.TYPE, 1);
        content2.put(Schema.Device.ROOM, "Room 1");
        getContentResolver().insert(Schema.Device.CONTENT_URI, content2);



        // Adding dummy Operation
        ContentValues content3 = new ContentValues();
        content3.put(Schema.Operation.NAME, "Operation 1");
        content3.put(Schema.Operation.IMPLEMENTATION, "pin2,TurnON ");
        content3.put(Schema.Operation.DEVICE_ID, 1);
        getContentResolver().insert(Schema.Operation.CONTENT_URI, content3);




        deviceListView = findViewById(R.id.device_list_view_Lay_retrieve_list_of_devices);



        //create instance of Controller , and send the list view and context because we need them in controller class
        retrieveListOfDevicesController = new RetrieveListOfDevicesController(RetrieveListOfDevicesBoundary.this ,deviceListView  );

        // true means there are devices , false no devices
        boolean isListEmpty = retrieveListOfDevicesController.retrieveDevices();

        if(isListEmpty ==false){
            Toast.makeText(this , "No Devices Available !" , Toast.LENGTH_LONG).show();
        }

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //save the selected list view item
                DeviceCategory deviceCategory = (DeviceCategory) parent.getItemAtPosition(position);

                Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveListOfDevicesBoundary.this , RetrieveSpecificDeviceBoundary.class);

                //send the info of selected category device
                openSpecificDeviceLayoutIntent.putExtra("ID" , deviceCategory.getId());


                startActivity(openSpecificDeviceLayoutIntent);





            }
        });
    }
}
