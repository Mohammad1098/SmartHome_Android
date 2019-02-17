package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.Operation.RetrieveListOfOperationBoundary;
import com.example.android.smarthome.R;

public class RetrieveSpecificDeviceBoundary extends AppCompatActivity {


    private ListView specificDeviceListView;
    private RetrieveSpecificDeviceController retrieveSpecificDeviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_specific_device);


        Intent intent = getIntent();

        long deviceCategory_ID = intent.getLongExtra("ID" ,0);



        specificDeviceListView = findViewById(R.id.specific_device_list_view_Lay_retrieve_list_of_specific_device);

        retrieveSpecificDeviceController = new RetrieveSpecificDeviceController(RetrieveSpecificDeviceBoundary.this, specificDeviceListView);

        boolean isListEmpty = retrieveSpecificDeviceController.retrieveSpecificDevices();

        if (isListEmpty == false) {
            Toast.makeText(this, "No Devices Available !", Toast.LENGTH_LONG).show();
        }

        specificDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //save the selected list view item
                Device device = (Device) parent.getItemAtPosition(position);

                Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveSpecificDeviceBoundary.this, RetrieveListOfOperationBoundary.class);

                //send the info of selected category device
                openSpecificDeviceLayoutIntent.putExtra("ID", device.getId());


                startActivity(openSpecificDeviceLayoutIntent);


            }
        });

    }

}
