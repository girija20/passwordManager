package com.gcop.passwordsafer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataListAdapter extends ArrayAdapter<UserInfo> {
//   boolean selection = false;
ListView  listView;

    Context context;

    public DataListAdapter(@NonNull Context context, ArrayList<UserInfo> list) {
        super(context, 0, list);
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView , @NonNull ViewGroup parent) {


        final View view = LayoutInflater.from(context).inflate(R.layout.body_activity, null);
        final UserInfo info = (UserInfo) getItem(position);
        TextView title = view.findViewById(R.id.title);
        CircleImageView circleImageView = view.findViewById(R.id.IconImage);
        if (info.image != null)
            circleImageView.setImageBitmap(CreateActivity.getBitmap(info.image));
        TextView website = view.findViewById(R.id.web);
        title.setText(info.title);
        website.setText(info.websites);










        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (selection) {


                } else {
*/
                    Intent intent = new Intent(context, CreateDetailsActivity.class);
                    intent.putExtra("id", info.id);
                    context.startActivity(intent);
//                }
            }
        });





      /*  view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                selection = true;
                return false;
            }
        });
*/


        return view;

    }

    private void notifyDataSetChanged(int position) {
        notifyDataSetChanged(position);

    }




}

