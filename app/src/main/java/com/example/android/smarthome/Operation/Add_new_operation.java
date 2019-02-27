package com.example.android.smarthome.Operation;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.Devices.Device;
import com.example.android.smarthome.Devices.RetrieveSpecificDeviceBoundary;
import com.example.android.smarthome.R;

public class Add_new_operation extends AppCompatActivity {


    private EditText operationNameEditText;

    private EditText operationImplementationEditText;

    private Button add_operation_button;

    private long selected_Device;

    private int type;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_operation);

        CreateViews();



    }


    private void CreateViews(){

        Intent intent = getIntent();

        selected_Device = intent.getLongExtra("DEVICEID" , -1);
        type = intent.getIntExtra("TYPE" , -1);

        operationNameEditText = findViewById(R.id.Operation_name_Lay_add_new_operation);

        operationImplementationEditText = findViewById(R.id.Operation_Implementation_Lay_add_new_operation);



        add_operation_button = findViewById(R.id.add_operation_button_Lay_add_new_operation);

        add_operation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addOperationToDB();
            }
        });


    }



    private void addOperationToDB(){

        String operationName = operationNameEditText.getText().toString().trim();

        String operationImplementation = operationImplementationEditText.getText().toString().trim();



        if(TextUtils.isEmpty(operationName) ){

            Toast.makeText(getApplicationContext() , "Please write the Operation Name" , Toast.LENGTH_LONG).show();
            return;

        }

        if(TextUtils.isEmpty(operationImplementation)){

            Toast.makeText(getApplicationContext() , "Please write the Operation Implementation" , Toast.LENGTH_LONG).show();
            return;

        }



        ContentValues contentValues = new ContentValues();

        contentValues.put(Schema.Operation.NAME , operationName);
        contentValues.put(Schema.Operation.IMPLEMENTATION , operationImplementation);
        contentValues.put(Schema.Operation.DEVICE_ID , selected_Device);

        getContentResolver().insert(Schema.Operation.CONTENT_URI, contentValues);


        returnToPreviousLayout();






    }


    private void returnToPreviousLayout(){

        Intent openListOfOperationLayoutIntent = new Intent(Add_new_operation.this, RetrieveListOfOperationBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openListOfOperationLayoutIntent.putExtra("DEVICEID", selected_Device);
        openListOfOperationLayoutIntent.putExtra("TYPE", type);



        startActivity(openListOfOperationLayoutIntent);


    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        returnToPreviousLayout();

                    }
                }).create().show();




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                returnToPreviousLayout();

                            }
                        }).create().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
