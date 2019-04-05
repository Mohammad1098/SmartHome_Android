package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.ShieldCategory;

import java.util.ArrayList;

public class Shield_Category_Spinner_Adapter extends ArrayAdapter<ShieldCategory> {



    public Shield_Category_Spinner_Adapter(Context context , ArrayList<ShieldCategory> list){

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_shield_category, parent, false
            );
        }


        ShieldCategory currentShieldCategory = getItem(position);



        ImageView ShieldCategoryImage =  convertView.findViewById(R.id.shield_category_Image_Lay_shield_category_spinner);

        TextView ShieldCategoryName =  convertView.findViewById(R.id.shield_category_Name_Lay_shield_category_spinner);

        TextView ShieldCategoryPin =  convertView.findViewById(R.id.shield_category_pin_number_Lay_shield_category_spinner);



        if(currentShieldCategory != null) {

            ShieldCategoryImage.setImageResource(currentShieldCategory.getShieldImage());

            ShieldCategoryName.setText(currentShieldCategory.getShieldName());

            ShieldCategoryPin.setText(String.valueOf(currentShieldCategory.getPin()));
        }
        return convertView;
    }
}
