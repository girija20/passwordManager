package com.gcop.passwordsafer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataListAutoCompleteAdapter extends ArrayAdapter<String> {



    Context context;
    public DataListAutoCompleteAdapter(@NonNull Context context, ArrayList<String>list) {
        super(context, 0,list);
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        final String info= (String) getItem(position);
        TextView title = new TextView(context);
        title.setText(info);

    /*    view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,CreateDetailsActivity.class);
                intent.putExtra("id",info);
                context.startActivity(intent);
            }
        });*/


        return title;
    }








}
