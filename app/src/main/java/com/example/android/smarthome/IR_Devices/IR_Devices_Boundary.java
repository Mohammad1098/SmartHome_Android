package com.example.android.smarthome.IR_Devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Devices.RetrieveDevicesBoundary;
import com.example.android.smarthome.Lights.LightBulbBoundary;
import com.example.android.smarthome.Operation.Add_new_operation;
import com.example.android.smarthome.Operation.Operation;
import com.example.android.smarthome.R;

public class IR_Devices_Boundary extends AppCompatActivity{

    private long deviceid,MicrocontrollerId;
    private int type;
    private FloatingActionButton addNewOperation;
    private ListView OperationList;
    private IR_Devices_Controller OperationController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_list_view);

        createViews();
        retrieveOperation();
        attachListViewToListener();
    }


    private void createViews(){

        Intent previousIntent = getIntent();

        MicrocontrollerId = previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1);
        deviceid = previousIntent.getLongExtra("DEVICEID" , -1);
        type = previousIntent.getIntExtra("TYPE" , -1);

        addNewOperation = findViewById(R.id.add_new_operation_Lay_operation_list_view);
        OperationList=findViewById(R.id.operation_list_view_Lay_operation_list_of_view);

        makeTitle(type);


        addNewOperation.setVisibility(View.VISIBLE);

        addNewOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewOperationIntent = new Intent(IR_Devices_Boundary.this , Add_new_operation.class);

                addNewOperationIntent.putExtra("MICROCONTROLLER_ID" ,MicrocontrollerId );
                addNewOperationIntent.putExtra("DEVICEID" ,deviceid );
                addNewOperationIntent.putExtra("TYPE" ,type );

                startActivity(addNewOperationIntent);



            }
        });


    }


    private void retrieveOperation(){



        OperationController = new IR_Devices_Controller(IR_Devices_Boundary.this , OperationList);

        boolean isListEmpty = OperationController.retrieveOperation(MicrocontrollerId , deviceid);

        if(isListEmpty ==false){
            Toast.makeText(this , "No Operations Available !" , Toast.LENGTH_LONG).show();
        }


    }

    private void makeTitle(int type){

        if(type==2){setTitle("TV");}
        if(type==3){setTitle("Receiver");}
        if(type==4){setTitle("AC");}


    }


    private void attachListViewToListener(){

        OperationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Operation currentOperation= (Operation) parent.getItemAtPosition(position);

                Toast.makeText(IR_Devices_Boundary.this , "Operation "+currentOperation.getName() +" has sent " ,Toast.LENGTH_LONG).show();
            }
        });

    }


    private void returnToPreviousLayout(){

        Intent intent = new Intent(IR_Devices_Boundary.this, RetrieveDevicesBoundary.class);

        intent.putExtra("MICROCONTROLLER_ID"  , MicrocontrollerId);
        intent.putExtra("DEVICEID"  , deviceid);
        intent.putExtra("TYPE"  , type);

        startActivity(intent);


    }


    @Override
    public void onBackPressed() {

        returnToPreviousLayout();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu , menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.delete :
                DeleteDevice();
                return true;
        }

        returnToPreviousLayout();
        return true;

    }


    private void DeleteDevice(){

        Log.e("dele" , "deeelete ");

        String selection = "MICROCONTROLLER_ID="+MicrocontrollerId +" AND _id="+deviceid;
        getContentResolver().delete(Schema.Device.CONTENT_URI ,selection , null );
        returnToPreviousLayout();

    }
}
