package com.example.birdBreeder.View.ui.Activites.MainActivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.View.ui.Dummy;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
     public static final String TAG = "com.example.birdBreeder.View.ui.Activites.MainActivity";
     private static int TIME = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
       if(actionBar != null){
           actionBar.setDisplayHomeAsUpEnabled(true);
           actionBar.setTitle(R.string.home);
           actionBar.show();
       }

        MainFragment mainFragment =  MainFragment.newInstance(Constants.HOME_ITEM);

        BrowserHelper.toFragment(this.getSupportFragmentManager() , mainFragment ,null);
        if(TIME==1) Dummy.addSpecies(getApplication());
        TIME++ ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //TODO: activate Search ;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


    }
}