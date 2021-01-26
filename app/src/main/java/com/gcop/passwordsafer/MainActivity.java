package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper databaseHelper;
    DataListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper= new DatabaseHelper(this);
        listView=findViewById(R.id.listView);
        adapter= new DataListAdapter(this,databaseHelper.getUserList());
        listView.setAdapter(adapter);



    }


}
