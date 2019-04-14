package com.example.android.smarthome.Shield;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.android.smarthome.Adapters.RetrieveListOfShieldsAdapter;

import java.util.ArrayList;

public class RetrieveShieldController extends AppCompatActivity {


    private RetrieveShieldDA retrieveShieldDA;
    private RetrieveListOfShieldsAdapter retrieveListOfShieldsAdapter;
    private Activity activity;
    private ListView listView;

    public RetrieveShieldController(Activity activity , ListView listView){

        this.activity = activity;
        this.listView = listView;

    }

    public RetrieveShieldController(Activity activity ){


        this.activity = activity;

    }



    public boolean retrieveShields(long MicroControllerID){


        retrieveShieldDA = new RetrieveShieldDA(this.activity , MicroControllerID);

        ArrayList<Shield> shieldArrayList = retrieveShieldDA.retrieveShields();

        if(shieldArrayList != null){

            retrieveListOfShieldsAdapter = new RetrieveListOfShieldsAdapter(activity.getApplicationContext() , shieldArrayList);
            this.listView.setAdapter(retrieveListOfShieldsAdapter);
            return true;

        }

        return false;

    }


    public ArrayList<ShieldCategory> returnRelayListSpinner(long MicroControllerID){


        retrieveShieldDA = new RetrieveShieldDA(this.activity , MicroControllerID);


        return retrieveShieldDA.returnRelayListSpinner();

    }


    public ArrayList<ShieldCategory> returnIRListListSpinner(long MicroControllerID){


        retrieveShieldDA = new RetrieveShieldDA(this.activity , MicroControllerID);


        return retrieveShieldDA.returnIRListListSpinner();

    }





}
