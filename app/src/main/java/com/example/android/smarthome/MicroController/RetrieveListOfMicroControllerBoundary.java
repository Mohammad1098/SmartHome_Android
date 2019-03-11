package com.example.android.smarthome.MicroController;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.Devices.RetrieveListOfDevicesBoundary;
import com.example.android.smarthome.R;




public class RetrieveListOfMicroControllerBoundary extends AppCompatActivity {

    private GridView microControllerGridView;
    private RetrieveListOfMicroController_Controller retrieveListOfMicroController_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_micro_controller);
        setTitle("MicroControllers");

        createViews();
        retrieveMicroControllers();
        attachListViewToListener();
    }




    private void createViews(){

        microControllerGridView = findViewById(R.id.micro_controller_list_view_Lay_retrieve_list_of_micro_controller);


        FloatingActionButton add_new_micro = findViewById(R.id.add_new_MicroController_Lay_retrieve_list_of_micro_controller);

        add_new_micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewDeviceIntent = new Intent(RetrieveListOfMicroControllerBoundary.this , Add_new_microController.class);


                startActivity(addNewDeviceIntent);


            }
        });

    }

    private void retrieveMicroControllers(){





        retrieveListOfMicroController_controller = new RetrieveListOfMicroController_Controller(RetrieveListOfMicroControllerBoundary.this , microControllerGridView);

        boolean isListEmpty = retrieveListOfMicroController_controller.retrieveMicroControllers();

        if(isListEmpty ==false){
            Toast.makeText(this , "No MicroControllers Available !" , Toast.LENGTH_LONG).show();
        }


    }

    private void attachListViewToListener(){

        microControllerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                MicroController currentMicroController = (MicroController) parent.getItemAtPosition(position);

                Intent SpecificMicroControllerIntent = new Intent(RetrieveListOfMicroControllerBoundary.this , RetrieveSpecificMicroControllerBoundary.class);

                SpecificMicroControllerIntent.putExtra("MICROCONTROLLER_ID", currentMicroController.getLong_id());

                startActivity(SpecificMicroControllerIntent);

            }
        });

    }

}
