package com.example.android.smarthome.Operation;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveListOfOperationAdapter;

import java.util.ArrayList;

public class RetrieveListOfOperationController extends AppCompatActivity {

    private RetrieveListOfOperationDA retrieveListOfOperationDA;
    private RetrieveListOfOperationAdapter retrieveListOfOperationAdapter;
    private Activity activity;
    private ListView listView;

    public RetrieveListOfOperationController(Activity activity , ListView listView){

        this.activity = activity;
        this.listView = listView;

    }


    public boolean retrieveListOfOperation(long id){

        retrieveListOfOperationDA = new RetrieveListOfOperationDA(this.activity , id);
        ArrayList<Operation> operationArrayList = retrieveListOfOperationDA.retrieveOperations();

        if(operationArrayList != null){

            Log.e("OperationController" , "id is "+id +"  Null");
            retrieveListOfOperationAdapter = new RetrieveListOfOperationAdapter(activity.getApplicationContext() , operationArrayList);

            listView.setAdapter(retrieveListOfOperationAdapter);

            return true;

        }

        return false;


    }



}
