package com.example.android.smarthome.MicroController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.R;




public class RetrieveListOfMicroControllerBoundary extends AppCompatActivity {

    private GridView microControllerGridView;
    private RetrieveListOfMicroController_Controller retrieveListOfMicroController_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_micro_controller);
        setTitle("MicroControllers");

        retrieveMicroControllers();
        attachListViewToListener();
    }


    private void retrieveMicroControllers(){


        microControllerGridView = findViewById(R.id.micro_controller_list_view_Lay_retrieve_list_of_micro_controller);


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




                //TODO

            }
        });

    }

}
