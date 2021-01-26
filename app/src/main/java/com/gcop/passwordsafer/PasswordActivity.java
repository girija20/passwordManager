package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Boolean firstTime;

    EditText password,cnPassword;
    ImageButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime", true);
        password=findViewById(R.id.password);
        cnPassword=findViewById(R.id.cnPassword);
        loginButton=findViewById(R.id.loginButton);



        if (firstTime){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String passwordValue = password.getText().toString();
                            String cnPasswordValue = cnPassword.getText().toString();

                            if (isFieldEmpty(password)&& isFieldEmpty(cnPassword)&& validate(password)){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                firstTime = false;
                                editor.putBoolean("firstTime", firstTime);
                                editor.putString("password", passwordValue);
                                editor.putString("cnPassword", cnPasswordValue);
                                editor.apply();

                                Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();


                            }

                        }
                    });

                }
            },50);
        }
        else {
            Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }





    }
    public static boolean isFieldEmpty(EditText view){
        String value = view.getText().toString();
        if (value.trim().length()>0){
            return true;
        }else {
            view.setError("enterValue");
            return false;
        }

    }

    private boolean validate(TextView view) {
        boolean temp=true;
        String passwordValue=password.getText().toString();
        String cnPasswordValue=cnPassword.getText().toString();


        if(!passwordValue.equals(cnPasswordValue)){
            Toast.makeText(PasswordActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }
}
