package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.smarthome.MicroController.MicroController_Category;
import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.Shield;

import java.util.ArrayList;

public class Shield_Spinner_Adapter extends ArrayAdapter<Shield> {



    public Shield_Spinner_Adapter(Context context , ArrayList<Shield> list){

        super(context , 0 , list );

    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {


        return  initView(position , convertView ,parent );

    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {


        return initView(position ,convertView , parent );
    }



    private View initView (int position, View convertView, ViewGroup parent){


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false
            );
        }


        Shield currentShield = getItem(position);



        ImageView shieldImage =  convertView.findViewById(R.id.category_Image_Lay_device_category);

        TextView shieldName =  convertView.findViewById(R.id.category_name_Lay_device_category);


        if(currentShield != null) {

            shieldImage.setImageResource(currentShield.getShieldImage());

            shieldName.setText(currentShield.getName());
        }


        return convertView;
    }
}