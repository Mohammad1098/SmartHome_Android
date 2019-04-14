package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.Operation.RetrieveListOfOperationBoundary;
import com.example.android.smarthome.R;

public class RetrieveMicroControllerDevicesBoundary extends AppCompatActivity {


    private ListView MicroControllerDeviceListView;
    private RetrieveMicroControllerDevicesController retrieveMicroControllerDevicesController;
    private long MicroControllerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_devices);

        setTitle("MicroController Devices");

        CreateAddDeviceButton();

        retrieveMicroControllerDevices();

        attachListViewToListener();




    }



    private void CreateAddDeviceButton(){


        FloatingActionButton floatingActionButtonAddNewDevice = findViewById(R.id.add_new_device_Lay_retrieve_list_of_devices);

        floatingActionButtonAddNewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewDeviceIntent = new Intent(RetrieveMicroControllerDevicesBoundary.this , Add_Predefined_Devices_Boundary.class);

                addNewDeviceIntent.putExtra("MICROCONTROLLER_ID" ,MicroControllerID );

                startActivity(addNewDeviceIntent);



            }
        });
    }

    private void retrieveMicroControllerDevices(){

        Intent previousintent = getIntent();

        MicroControllerID =  previousintent.getLongExtra("MICROCONTROLLER_ID" , -1);

        MicroControllerDeviceListView = findViewById(R.id.device_list_view_Lay_retrieve_list_of_specific_device);

        retrieveMicroControllerDevicesController = new RetrieveMicroControllerDevicesController(RetrieveMicroControllerDevicesBoundary.this, MicroControllerDeviceListView);

        boolean isListEmpty = retrieveMicroControllerDevicesController.retrieveMicroControllerDevices(MicroControllerID);


        if (isListEmpty == false) {
            Toast.makeText(this, "No Devices Available !", Toast.LENGTH_LONG).show();
        }



    }


    private void attachListViewToListener(){

        MicroControllerDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //save the selected list view item
                Device device = (Device) parent.getItemAtPosition(position);

                Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveMicroControllerDevicesBoundary.this, RetrieveListOfOperationBoundary.class);


                //send the id of selected device to RetrieveListOfOperationBoundary class
                openSpecificDeviceLayoutIntent.putExtra("DEVICEID", device.getId());
                openSpecificDeviceLayoutIntent.putExtra("TYPE", device.getType());






                startActivity(openSpecificDeviceLayoutIntent);


            }
        });

    }


    private void returnToPreviousLayout(){

        /*Intent openSpecificMicroControllerLayoutIntent = new Intent(RetrieveMicroControllerDevicesBoundary.this, RetrieveSpecificMicroControllerBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openSpecificMicroControllerLayoutIntent.putExtra("MICROCONTROLLER_ID", MicroControllerID);


        startActivity(openSpecificMicroControllerLayoutIntent);
        */

    }

    @Override
    public void onBackPressed() {



        returnToPreviousLayout();



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        returnToPreviousLayout();
        return true;




    }


}