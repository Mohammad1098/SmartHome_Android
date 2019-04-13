package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.R;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;


public class RetrieveListOfDevicesBoundary extends AppCompatActivity {


    private ListView deviceListView;
    private FloatingActionButton add_device_btn;
    private RetrieveListOfDevicesController retrieveListOfDevicesController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_devices);
        setTitle("All Devices");


        retrieveDevices();
        attachListViewToListener();


    }


    private void retrieveDevices(){

        deviceListView = findViewById(R.id.device_list_view_Lay_retrieve_list_of_specific_device);

        add_device_btn = findViewById(R.id.add_new_device_Lay_retrieve_list_of_devices);

        add_device_btn.setVisibility(View.GONE);

        //create instance of Controller , and send the list view and context because we need them in controller class
        retrieveListOfDevicesController = new RetrieveListOfDevicesController(RetrieveListOfDevicesBoundary.this ,deviceListView  );

        // true means there are devices , false no devices
        boolean isListEmpty = retrieveListOfDevicesController.retrieveDevices();

        if(isListEmpty ==false){
            Toast.makeText(this , "No Devices Available !" , Toast.LENGTH_LONG).show();
        }


    }


    private void attachListViewToListener(){

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //save the selected list view item
                DeviceCategory deviceCategory = (DeviceCategory) parent.getItemAtPosition(position);

                Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveListOfDevicesBoundary.this , RetrieveMicroControllerDevicesBoundary.class);

                //send the info of selected category device
//                openSpecificDeviceLayoutIntent.putExtra("ID" , deviceCategory.getId());
                openSpecificDeviceLayoutIntent.putExtra("TYPE" , deviceCategory.getType());


                startActivity(openSpecificDeviceLayoutIntent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO call function to retrieve devices
    }
}
