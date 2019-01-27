package com.example.android.smarthome.Devices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.smarthome.LightBulb.LightBulbBoundary;
import com.example.android.smarthome.R;

public class RetrieveListOfDevicesController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_list_of_devices_boundary);

//        Intent intent = new Intent(this , LightBulbBoundary.class);
//        startActivity(intent);
    }

    public void lightActivity(View view){
        Intent intent = new Intent(this, LightBulbBoundary.class);
        startActivity(intent);
    }
}
