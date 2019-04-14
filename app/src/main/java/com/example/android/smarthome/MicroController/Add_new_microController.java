package com.example.android.smarthome.MicroController;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.android.smarthome.Adapters.MicroController_Category_Spinner_Adapter;
import com.example.android.smarthome.DataBase.Schema;
import com.example.android.smarthome.R;
import java.util.ArrayList;


public class Add_new_microController extends AppCompatActivity {

    private Spinner microControllerSpinner;

    private EditText microControllerRoomEditText;

    private Button add_microController_button;

    private MicroController_Category_Spinner_Adapter microControllerSpinnerAdapter;

    private ArrayList<MicroController_Category> microController_categoryArrayList = new ArrayList<>();

    private String selectedMicroControllerName;

    private int selectedMicroController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_microcontroller);

        CreateViews();
        CreateSpinnerAdapter();
        attachSpinnerToListener();
    }




    private ArrayList<MicroController_Category> CreateMicroControllerCategory(){

        microController_categoryArrayList.add(new MicroController_Category("Arduino Uno R3 " , R.drawable.arduinoicon , 0 ));

        return microController_categoryArrayList;
    }


    private void CreateSpinnerAdapter(){

        microControllerSpinnerAdapter =  new MicroController_Category_Spinner_Adapter(this , CreateMicroControllerCategory());

        microControllerSpinner = findViewById(R.id.spinner_microController_Lay_add_new_microController);

        microControllerSpinner.setAdapter(microControllerSpinnerAdapter);

    }


    private void attachSpinnerToListener(){


        microControllerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MicroController_Category MicroController = (MicroController_Category) parent.getItemAtPosition(position);

                selectedMicroControllerName = MicroController.getCategoryName();
                selectedMicroController = MicroController.getType();  // Type of micro controller  0 --> Arduino_Uno R3   ,   1 --> Nano


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedMicroController = -1;

            }
        });


    }

    private void CreateViews(){


        setTitle("Add new MicroController");
        microControllerRoomEditText = findViewById(R.id.micro_controller_room_Lay_add_new_microcontroller);

        add_microController_button = findViewById(R.id.add_microController_Lay_add_new_microController);

        add_microController_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDeviceToDB();

            }
        });


    }


    private void addDeviceToDB(){


        String microControllerRoom = microControllerRoomEditText.getText().toString().trim();


        if(TextUtils.isEmpty(microControllerRoom) ){

            Toast.makeText(getApplicationContext() , "Please Specify Room " , Toast.LENGTH_LONG).show();
            return;

        }




        if(selectedMicroController == -1){

            Toast.makeText(getApplicationContext() , "Please select MicroController Category" , Toast.LENGTH_LONG).show();
            return;

        }


        ContentValues contentValuesMicroControllerTable = new ContentValues();

        contentValuesMicroControllerTable.put(Schema.MicroController.NAME , selectedMicroControllerName);
        contentValuesMicroControllerTable.put(Schema.MicroController.TYPE , selectedMicroController);
        contentValuesMicroControllerTable.put(Schema.MicroController.ROOM , microControllerRoom);
        Uri uri = getContentResolver().insert(Schema.MicroController.CONTENT_URI, contentValuesMicroControllerTable);

        long MicroControllerID = Long.valueOf(uri.getLastPathSegment());



        // insert the required pins for
        ArrayList<ContentValues> contentValuesPinTable = returnPinTableContentValues(selectedMicroController , MicroControllerID);

        for (int i = 0 ; i < contentValuesPinTable.size() ; i++){


            getContentResolver().insert(Schema.Pin.CONTENT_URI, contentValuesPinTable.get(i));


        }



        returnToPreviousLayout();


    }





    private ArrayList<ContentValues> returnPinTableContentValues(int type_micorController , long microControllerId){


        switch (type_micorController){


            case 0 :
                Arduino_Uno_R3 arduinoUnoR3 = new Arduino_Uno_R3(microControllerId);
                return arduinoUnoR3.returnPinTableContentValues();
            case 1 :
                Arduino_Nano arduino_nano = new Arduino_Nano(microControllerId);
                return arduino_nano.returnPinTableContentValues();

            default: return null;

        }


    }



    private void returnToPreviousLayout(){

        Intent Intent = new Intent(Add_new_microController.this, RetrieveListOfMicroControllerBoundary.class);


        startActivity(Intent);


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
