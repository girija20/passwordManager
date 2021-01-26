package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    Button btn_change_password;
    EditText current_password,new_password, confirm_password;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password2);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        current_password=findViewById(R.id.current_password);
        new_password=findViewById(R.id.new_password);
        confirm_password=findViewById(R.id.confirm_password);



        btn_change_password= findViewById(R.id.btn_change_password);

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nwPasswordValue = new_password.getText().toString();
                String cmPasswordValue = confirm_password.getText().toString();
                String cuPasswordValue = current_password.getText().toString();


                if (isFieldEmpty(current_password) && isFieldEmpty(new_password) && isFieldEmpty(confirm_password) && validate(new_password)) {
                         if (cuPasswordValue.equals(sharedPreferences.edit())) {
                             Toast.makeText(ChangePasswordActivity.this, "password incorrect", Toast.LENGTH_SHORT).show();


                             SharedPreferences.Editor editor = sharedPreferences.edit();
                             editor.putString("new_password", nwPasswordValue);
                             editor.putString("cmPassword", cmPasswordValue);
                             editor.apply();

                             Toast.makeText(ChangePasswordActivity.this, "password has changed", Toast.LENGTH_SHORT).show();
                         }else {
                             Toast.makeText(ChangePasswordActivity.this, "try again", Toast.LENGTH_SHORT).show();
                         }


                }
            }
        });

        }



    public boolean isFieldEmpty(EditText view){
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
        String nwPasswordValue=new_password.getText().toString();
        String cmPasswordValue=confirm_password.getText().toString();


        if(!nwPasswordValue.equals(cmPasswordValue)){
            Toast.makeText(ChangePasswordActivity.this,"not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }
}
