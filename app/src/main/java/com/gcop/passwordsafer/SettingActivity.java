package com.gcop.passwordsafer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    TextView about,passChange, eraseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        about=findViewById(R.id.about);
        passChange=findViewById(R.id.passChange);
        eraseData= findViewById(R.id.eraseData);
        databaseHelper=new DatabaseHelper(this);


        passChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ChangePasswordActivity.class));



            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
            }
        });

        eraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAppData();

            }
        });
    }




    private void clearAppData() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All AppData");
        builder.setMessage("This will erase all application's data and settings from your device");


        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            try {
                // clearing app data
                if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                    ((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData(); // note: it has a return value!
                } else {
                    String packageName = getApplicationContext().getPackageName();
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("pm clear "+packageName);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    });

        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
        builder.show();
}
}
