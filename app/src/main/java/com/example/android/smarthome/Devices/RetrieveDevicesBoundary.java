package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.android.smarthome.DeviceCategory.DeviceCategoryBoundary;
import com.example.android.smarthome.Lights.LightBulbBoundary;
import com.example.android.smarthome.R;
import com.example.android.smarthome.RGB_LED.RGBLEDBoundary;

public class RetrieveDevicesBoundary extends AppCompatActivity{


    private GridView devicesGridView;
    private RetrieveDevicesController devicesController;
    private int type;
    private long MicrocontrollerId;
    private FloatingActionButton addNewDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);

        createViews();
        retrieveDevices();
        attachListViewToListener();
    }


    private void createViews(){

        Intent previousIntent = getIntent();

        type = previousIntent.getIntExtra("TYPE" , -1);
        makeTitle(type);
        MicrocontrollerId = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);
        devicesGridView = findViewById(R.id.device_list_view_Lay_device_list);


    }


    private void makeTitle(int type){

        if(type ==100){createAddDeviceButton();}
        else {removeAddDeviceButton();}

        if(type==0){setTitle("Light Bulb");}
        if(type==1){setTitle("RGB LED");}
        if(type==2){setTitle("TV");}
        if(type==3){setTitle("Receiver");}
        if(type==4){setTitle("AC");}
        if(type==100){setTitle("All devices");}


    }


    private void createAddDeviceButton(){

       addNewDevice = findViewById(R.id.add_new_device_Lay_device_list);

        addNewDevice.setVisibility(View.VISIBLE);

        addNewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewDeviceIntent = new Intent(RetrieveDevicesBoundary.this , Add_Predefined_Devices_Boundary.class);

                addNewDeviceIntent.putExtra("MICROCONTROLLER_ID" ,MicrocontrollerId );

                startActivity(addNewDeviceIntent);



            }
        });

    }

    private void removeAddDeviceButton(){

        addNewDevice.setVisibility(View.GONE);


    }

    private void retrieveDevices(){



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

            case 1 :
                return  new Intent(RetrieveDevicesBoundary.this , RGBLEDBoundary.class);

            default:
                return null;
        }


    }

    private void returnToPreviousLayout(){

        Intent intent = new Intent(RetrieveDevicesBoundary.this, DeviceCategoryBoundary.class);

        intent.putExtra("MICROCONTROLLER_ID"  , MicrocontrollerId);

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
