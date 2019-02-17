package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.R;
import java.util.ArrayList;


public class Device_Category_Spinner_Adapter extends ArrayAdapter<DeviceCategory> {



    public Device_Category_Spinner_Adapter(Context context , ArrayList<DeviceCategory> list){

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_category, parent, false
            );
        }


        DeviceCategory currentCategory = getItem(position);



        ImageView categoryImage =  convertView.findViewById(R.id.category_Image_Lay_device_category);

        TextView categoryName =  convertView.findViewById(R.id.category_name_Lay_device_category);


        if(currentCategory != null) {
            categoryImage.setImageResource(currentCategory.getCategoryImage());

            categoryName.setText(currentCategory.getCategoryName());
        }
        return convertView;
    }
}
