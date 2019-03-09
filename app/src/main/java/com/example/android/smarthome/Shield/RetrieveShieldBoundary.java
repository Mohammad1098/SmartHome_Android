package com.example.android.smarthome.Shield;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smarthome.R;

public class RetrieveShieldBoundary extends AppCompatActivity {


    private ListView shieldListView;
    private RetrieveShieldController retrieveShieldController;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_shields);

        createViews();
        retrieveShields();
    }



    private void createViews(){

        shieldListView = findViewById(R.id.shield_list_view_Lay_retrieve_list_of_shields);

    }

    private void retrieveShields(){

        Intent previousIntent = getIntent();

        retrieveShieldController = new RetrieveShieldController(this ,  shieldListView);

        boolean isListEmpty = retrieveShieldController.retrieveShields(previousIntent.getLongExtra("MICROCONTROLLER_ID" , -1));

        if (isListEmpty == false) {
            Toast.makeText(this, "No Shields Available !", Toast.LENGTH_LONG).show();
        }

    }


}
