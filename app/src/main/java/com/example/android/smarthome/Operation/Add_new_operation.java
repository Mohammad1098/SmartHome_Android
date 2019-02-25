package com.example.android.smarthome.Operation;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.R;

public class Add_new_operation extends AppCompatActivity {


    private EditText operationNameEditText;

    private EditText operationImplementationEditText;

    private Button add_operation_button;

    private long selected_Device;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_operation);

        CreateViews();

        Intent intent = getIntent();

        selected_Device = intent.getLongExtra("DEVICEID" , -1);


    }


    private void CreateViews(){

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

        Log.e("Add new Operation" ,String.valueOf(selected_Device) );


        finish();


    }
}
