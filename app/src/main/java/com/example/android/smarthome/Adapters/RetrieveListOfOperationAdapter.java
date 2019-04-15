package com.example.android.smarthome.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.smarthome.Operation.Operation;
import com.example.android.smarthome.R;

import java.util.ArrayList;

public class RetrieveListOfOperationAdapter extends ArrayAdapter<Operation> {


    public RetrieveListOfOperationAdapter(Context context , ArrayList<Operation> list){

        super(context , 0  , list);

    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.operation_list_item , parent , false);

        }

        Operation currentOperation = getItem(position);


        TextView operationName = convertView.findViewById(R.id.operation_name_Lay_retrieve_list_of_operation_item);

        operationName.setText(currentOperation.getName());


        return convertView;

    }


}
