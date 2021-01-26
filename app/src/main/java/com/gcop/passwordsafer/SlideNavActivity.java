package com.gcop.passwordsafer;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import hotchemi.android.rate.AppRate;

public class SlideNavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionMode mActionMode;
    ListView listView;
    DatabaseHelper databaseHelper;
    DataListAdapter adapter;
    SharedPreferences sharedPreferences;


    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_nav);

        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
//        AppRate.with(this).showRateDialog(this);

        sharedPreferences = getSharedPreferences("UserInfo", 0);


        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);


        adapter = new DataListAdapter(this, databaseHelper.getUserList());
        listView.setAdapter(adapter);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlideNavActivity.this, CreateActivity.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_dailyUses) {
            startActivity(new Intent(SlideNavActivity.this, DailyUsesActivity.class));

        } else if (id == R.id.nav_Private) {
            Intent intent = new Intent(SlideNavActivity.this, PrivateActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_Business) {

            startActivity(new Intent(SlideNavActivity.this,BusinessActivity.class));


        } else if (id == R.id.nav_recycleBin) {

            startActivity(new Intent(SlideNavActivity.this, RecycleBinActivity.class));

        } */if (id == R.id.nav_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "share via"));


        } else if (id == R.id.nav_Setting) {
            startActivity(new Intent(SlideNavActivity.this, SettingActivity.class));

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.slide_nav, menu);


MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });




        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                sharedPreferences.edit().putBoolean("rememberMe", false).commit();
                startActivity(new Intent(SlideNavActivity.this, LoginActivity.class));
                finish();


                break;




        }
        return super.onOptionsItemSelected(item);
    }





   //activity lifecycle


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("lifecycle", "onResume");
        adapter.notifyDataSetChanged();
        super.onResume();
        runOnUiThread(run);


    }


    @Override
    protected void onPause() {

        super.onPause();

        Log.i("lifecycle", "onPause");

    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.i("lifecycle", "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lifecycle", "onRestart");

    }

    @Override
    protected void onDestroy() {




        super.onDestroy();
        Log.i("lifecycle", "onDestroy");

    }



    Runnable run = new Runnable() {
        public void run() {

            adapter.notifyDataSetChanged();
            listView.invalidateViews();
            listView.refreshDrawableState();
        }
    };




}



