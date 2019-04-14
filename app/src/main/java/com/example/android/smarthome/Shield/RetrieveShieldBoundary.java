package com.example.android.smarthome.Shield;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.DeviceCategory.DeviceCategoryBoundary;
import com.example.android.smarthome.R;

public class RetrieveShieldBoundary extends AppCompatActivity {


    private ListView shieldListView;
    private RetrieveShieldController retrieveShieldController;
    private FloatingActionButton btn_Add_New_Shield;
    private long MicroControllerID;


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_shields);
        setTitle("Shield Boundary");
        createViews();
        retrieveShields();
    }



    private void createViews(){
        Intent previousIntent = getIntent();

        MicroControllerID = previousIntent.getLongExtra("MICROCONTROLLER_ID", -1);


        shieldListView = findViewById(R.id.shield_list_view_Lay_retrieve_list_of_shields);

        btn_Add_New_Shield = findViewById(R.id.add_new_Shield_Lay_retrieve_list_of_shields);

        btn_Add_New_Shield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addNewShieldIntent = new Intent(RetrieveShieldBoundary.this , Add_new_Shield.class);

                addNewShieldIntent.putExtra("MICROCONTROLLER_ID" ,MicroControllerID );

                startActivity(addNewShieldIntent);


            }
        });
    }

    private void retrieveShields(){


        retrieveShieldController = new RetrieveShieldController(this ,  shieldListView);


        boolean isListEmpty = retrieveShieldController.retrieveShields( MicroControllerID);

        if (isListEmpty == false) {
            Toast.makeText(this, "No Shields Available !", Toast.LENGTH_LONG).show();
        }

    }



    private void returnToPreviousLayout(){



        Intent openSpecificMicroControllerLayoutIntent = new Intent(RetrieveShieldBoundary.this, DeviceCategoryBoundary.class);


        //send the id of selected device to RetrieveListOfOperationBoundary class
        openSpecificMicroControllerLayoutIntent.putExtra("MICROCONTROLLER_ID", MicroControllerID);


        startActivity(openSpecificMicroControllerLayoutIntent);


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
