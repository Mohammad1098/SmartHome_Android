package com.example.android.smarthome.Operation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.Devices.RetrieveMicroControllerDevicesBoundary;
import com.example.android.smarthome.R;

public class RetrieveListOfOperationBoundary extends AppCompatActivity {


    private ListView operationListView;
    private RetrieveListOfOperationController retrieveListOfOperationController;
    private long device_ID;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_list_view);

        setTitle("Operation");


        Intent intent = getIntent();

        device_ID = intent.getLongExtra("DEVICEID" , -1);
        type= intent.getIntExtra("TYPE" , -1);

        Log.e("OperationBoundary oncre" ,String.valueOf(type) +"   "+String.valueOf(device_ID) );

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
                addNewOperationIntent.putExtra("TYPE" , type );


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


    @Override
    public void onBackPressed() {

        returnToPreviousLayout();


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnToPreviousLayout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void returnToPreviousLayout(){

        Intent openSpecificDeviceLayoutIntent = new Intent(RetrieveListOfOperationBoundary.this, RetrieveMicroControllerDevicesBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openSpecificDeviceLayoutIntent.putExtra("TYPE", type);


        startActivity(openSpecificDeviceLayoutIntent);


    }


    }
