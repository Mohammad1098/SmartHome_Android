package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.smarthome.R;
import com.example.android.smarthome.Shield.Shield;

import java.util.ArrayList;

public class RetrieveListOfShieldsAdapter extends ArrayAdapter<Shield> {



    public RetrieveListOfShieldsAdapter(Context context , ArrayList<Shield> list){

        super(context , 0 , list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shield_list_item, parent, false);
        }


        Shield currentShield = getItem(position);

        TextView shieldName = convertView.findViewById(R.id.shield_name_Lay_shield_list_item);

        shieldName.setText(currentShield.getName());


        ImageView shieldImage = convertView.findViewById(R.id.shield_image_Lay_shield_list_item);

        returnImageId(currentShield.getType() , shieldImage);

        return convertView;
    }


    private int returnImageId(int type , ImageView imageView){



        switch (type){

            case 1 :
                imageView.setImageResource(R.drawable.ic_relay);
                break;
            case 2 :
                imageView.setImageResource(R.drawable.ic_ir);
                break;

        }

        return 0;

    }

}
