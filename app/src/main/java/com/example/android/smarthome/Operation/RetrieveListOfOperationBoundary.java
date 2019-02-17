package com.example.android.smarthome.Operation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.smarthome.R;

public class RetrieveListOfOperationBoundary extends AppCompatActivity {


    private ListView operationListView;
    private RetrieveListOfOperationController retrieveListOfOperationController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_list_view);

        Intent retrieveListOfDevicesIntent = getIntent();

        long device_ID = retrieveListOfDevicesIntent.getLongExtra("ID" ,0);



        operationListView = findViewById(R.id.operation_list_view_Lay_operation_list_of_view);

        retrieveListOfOperationController = new RetrieveListOfOperationController(this , operationListView);

        boolean isListEmpty = retrieveListOfOperationController.retrieveListOfOperation(device_ID);

        if(isListEmpty ==false){
            Toast.makeText(this , "No Operation Available !" , Toast.LENGTH_LONG).show();
        }

    }
}
