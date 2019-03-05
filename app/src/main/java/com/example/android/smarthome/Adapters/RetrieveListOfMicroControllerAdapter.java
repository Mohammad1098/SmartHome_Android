package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.smarthome.DeviceCategory.DeviceCategory;
import com.example.android.smarthome.MicroController.MicroController;
import com.example.android.smarthome.R;
import java.util.ArrayList;

public class RetrieveListOfMicroControllerAdapter extends ArrayAdapter<MicroController> {


    public RetrieveListOfMicroControllerAdapter(Context context , ArrayList<MicroController> list){


        super(context , 0 , list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.micro_controller_list_item, parent , false);
        }


        MicroController currentMicroController = getItem(position);


        TextView microControllerName = (TextView) convertView.findViewById(R.id.micro_controller_name_Lay_micro_controller_list_item);

        microControllerName.setText(currentMicroController.getName());

        //TODO the Image as well

        return convertView;


    }


}