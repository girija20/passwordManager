package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText password;
    ImageButton loginButton;


    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("UserInfo",0);
        password= findViewById(R.id.password);
        loginButton= findViewById(R.id.loginButton);




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordValue = password.getText().toString();

                String registeredPasswordValue = sharedPreferences.getString("password", "T");


                    if (passwordValue.equals(registeredPasswordValue)) {
                        startActivity(new Intent(LoginActivity.this, SlideNavActivity.class));
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();


                    }


                }

        });



    }



}
