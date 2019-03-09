package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.Operation.RetrieveListOfOperationBoundary;
import com.example.android.smarthome.R;

public class RetrieveSpecificDeviceBoundary extends AppCompatActivity {


    private ListView specificDeviceListView;
    private RetrieveSpecificDeviceController retrieveSpecificDeviceController;
    private int type;
    private long MicroControllerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_specific_device);

        setTitle("Specific Device");

        CreateAddDeviceButton();

        retrieveSpecificDevices();

        attachListViewToListener();




    }



    private void CreateAddDeviceButton(){


        FloatingActionButton floatingActionButtonAddNewDevice = findViewById(R.id.add_new_device_Lay_retrieve_list_of_specific_devices);

        floatingActionButtonAddNewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewDeviceIntent = new Intent(RetrieveSpecificDeviceBoundary.this , Add_new_device.class);

                addNewDeviceIntent.putExtra("TYPE" ,type );

                startActivity(addNewDeviceIntent);



            }
        });
    }

    private void retrieveSpecificDevices(){


        specificDeviceListView = findViewById(R.id.specific_device_list_view_Lay_retrieve_list_of_specific_device);

        retrieveSpecificDeviceController = new RetrieveSpecificDeviceController(RetrieveSpecificDeviceBoundary.this, specificDeviceListView);

        Intent previousintent = getIntent();

        type = previousintent.getIntExtra("TYPE" , -1);

        MicroControllerID =  previousintent.getLongExtra("MICROCONTROLLER_ID" , -1);

        boolean isListEmpty=false;

        if(type != -1){

            isListEmpty = retrieveSpecificDeviceController.retrieveSpecificDevicesByCategory(type);

        }

        if(MicroControllerID != -1){

            isListEmpty = retrieveSpecificDeviceController.retrieveSpecificDevicesByMicroController(MicroControllerID);

        }




        if (isListEmpty == false) {
            Toast.makeText(this, "No Devices Available !", Toast.LENGTH_LONG).show();
        }



    }


    private void attachListViewToListener(){

        specificDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //save the selected list view item
                Device device = (Device) parent.getItemAtPosition(position);

                Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveSpecificDeviceBoundary.this, RetrieveListOfOperationBoundary.class);


                //send the id of selected device to RetrieveListOfOperationBoundary class
                openSpecificDeviceLayoutIntent.putExtra("DEVICEID", device.getId());
                openSpecificDeviceLayoutIntent.putExtra("TYPE", device.getType());






                startActivity(openSpecificDeviceLayoutIntent);


            }
        });

    }


}
