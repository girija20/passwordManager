package com.gcop.passwordsafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class DailyUsesActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_uses);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.slide_nav, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                sharedPreferences.edit().putBoolean("rememberMe", false).commit();
                startActivity(new Intent(DailyUsesActivity.this, LoginActivity.class));
                finish();


                break;

            case R.id.search_bar:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
