package com.example.android.smarthome.Operation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.Devices.Add_new_device;
import com.example.android.smarthome.Devices.RetrieveSpecificDeviceBoundary;
import com.example.android.smarthome.Devices.RetrieveSpecificDeviceController;
import com.example.android.smarthome.R;

public class RetrieveListOfOperationBoundary extends AppCompatActivity {


    private ListView operationListView;
    private RetrieveListOfOperationController retrieveListOfOperationController;
    private long device_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_list_view);

        setTitle("Operation");


        Intent intent = getIntent();

        device_ID = intent.getLongExtra("DEVICEID" , -1);

        Log.e("OperationBoundary" ,String.valueOf(device_ID) );

        CreateAddOperationButton();
        retrieveOperation();




    }


    private void CreateAddOperationButton(){


        FloatingActionButton floatingActionButtonAddNewOperation = findViewById(R.id.add_new_operation_Lay_operation_list_view);

        floatingActionButtonAddNewOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewOperationIntent = new Intent(RetrieveListOfOperationBoundary.this , Add_new_operation.class);


                addNewOperationIntent.putExtra("DEVICEID" , device_ID );

                startActivity(addNewOperationIntent);



            }
        });
    }



    private void retrieveOperation(){




        operationListView = findViewById(R.id.operation_list_view_Lay_operation_list_of_view);

        retrieveListOfOperationController = new RetrieveListOfOperationController(this , operationListView);

        boolean isListEmpty = retrieveListOfOperationController.retrieveListOfOperation(device_ID);

        if(isListEmpty ==false){
            Toast.makeText(this , "No Operation Available !" , Toast.LENGTH_LONG).show();
        }



    }
}
