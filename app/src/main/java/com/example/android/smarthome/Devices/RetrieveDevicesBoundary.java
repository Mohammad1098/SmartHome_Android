package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.android.smarthome.DeviceCategory.DeviceCategoryBoundary;
import com.example.android.smarthome.Lights.LightBulbBoundary;
import com.example.android.smarthome.R;

public class RetrieveDevicesBoundary extends AppCompatActivity{


    private GridView devicesGridView;
    private RetrieveDevicesController devicesController;
    private int type;
    private long MicrocontrollerId;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);

        createViews();
        retrieveMicroControllers();
        attachListViewToListener();
    }


    private void createViews(){

        Intent previousIntent = getIntent();

        type = previousIntent.getIntExtra("TYPE" , -1);
        MicrocontrollerId = previousIntent.getLongExtra("MicrocontrollerId" , -1);
        devicesGridView = findViewById(R.id.device_list_view_Lay_device_list);


    }


    private void retrieveMicroControllers(){



        devicesController = new RetrieveDevicesController(RetrieveDevicesBoundary.this , devicesGridView);

        boolean isListEmpty = devicesController.retrieveDevices(MicrocontrollerId , type);

        if(isListEmpty ==false){
            Toast.makeText(this , "No Devices Available !" , Toast.LENGTH_LONG).show();
        }


    }


    private void attachListViewToListener(){

        devicesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Device currentDevice = (Device) parent.getItemAtPosition(position);




                Intent SpecificDeviceIntent = returnApproriteIntent(currentDevice.getType());

                SpecificDeviceIntent.putExtra("MICROCONTROLLER_ID", currentDevice.getMicroController_Id());
                SpecificDeviceIntent.putExtra("TYPE" , currentDevice.getType());


                startActivity(SpecificDeviceIntent);

            }
        });

    }


    private Intent returnApproriteIntent(int type){

        switch (type){

            case 0 :
                return  new Intent(RetrieveDevicesBoundary.this , LightBulbBoundary.class);

            default:
                return null;
        }


    }

    private void returnToPreviousLayout(){

        Intent intent = new Intent(RetrieveDevicesBoundary.this, DeviceCategoryBoundary.class);

        intent.putExtra("MicrocontrollerId"  , MicrocontrollerId);

        startActivity(intent);


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
