package com.gcop.passwordsafer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.prefs.Preferences;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateDetailsActivity extends AppCompatActivity {

    TextView title, userId, password, web;
    CircleImageView circleImageView;
    String id;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_details);

        databaseHelper = new DatabaseHelper(this);

        id = getIntent().getStringExtra("id");

        title = findViewById(R.id.title);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        web = findViewById(R.id.web);
        circleImageView = findViewById(R.id.profile_image);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("DetailActiity", "id:" + id);
                Intent intent = new Intent(CreateDetailsActivity.this, CreateActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);

            }
        });


    }


    public void populateData() {

        UserInfo info = databaseHelper.getUserInfo(id);

        title.setText(info.title);
        userId.setText(info.userId);
        password.setText(info.password);
        web.setText(info.websites);
        if (info.image != null)
            circleImageView.setImageBitmap(CreateActivity.getBitmap(info.image));


    }

    @Override
    protected void onResume() {
        super.onResume();
         populateData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_delete:


                showAlertDialog();


            break;

           /* case R.id.levels:

                showAlertDialogCheckBox();
*/
        }


        return super.onOptionsItemSelected(item);


    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("delete user");
        builder.setMessage("are you sure");

       /* CheckBox checkBox;
        checkBox.setText("private");*/
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             /* startActivity(new Intent(CreateDetailsActivity.this, RecycleBinActivity.class));
               Intent shareIntent = new Intent();
                 shareIntent = Intent.putExtra(Intent.EXTRA_TEXT, "databaseHelper.deleteUser(id)" );
*/
                databaseHelper.deleteUser(id);
                finish();

            }
        });

        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


    public void showAlertDialogCheckBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select levels");

        // add a checkbox list
        final String[] levels = {"", "DailyUses", "Private", "Business"};
        final boolean[] checkedItems = {true, false, false, false};
        builder.setMultiChoiceItems(levels, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (levels[which].equals("DailyUses")){


                }

                else if (levels[which].equals("private")){

                }

                else if (levels[which].equals("Business")){

                }


            }
        });

// add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (levels[which].equals("DailyUses")){


                }

                else if (levels[which].equals("private")){

                }

                else if (levels[which].equals("Business")){

                }


            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
