package com.example.birdBreeder.View.ui.Activites.MainActivity;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.ui.Birds.BirdsFragment;
import com.example.birdBreeder.View.ui.Dummy;
import com.example.birdBreeder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ActivityMainBinding binding ;
//     public static final String TAG = "com.example.birdBreeder.View.ui.Activites.MainActivity";
     private static int TIME = 1 ;
     private  int selectedItem ;
    private  ActionBar actionBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        actionBar = getSupportActionBar();
       if(actionBar != null){
//           actionBar.setDisplayHomeAsUpEnabled(true);
           actionBar.setTitle(R.string.home);
           actionBar.show();
       }

       if(savedInstanceState!=null){
           selectedItem = savedInstanceState.getInt(Constants.SELECTED_ITEM);
           binding.navigator.setSelectedItemId(getSelectedId());
       }

        binding.navigator.setOnNavigationItemSelectedListener(item -> seSelected(item.getItemId()));
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Constants.SELECTED_ITEM , selectedItem);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.navigator.setSelectedItemId(getSelectedId());
        binding.navigator.setVisibility(View.VISIBLE);
    }


    @SuppressLint("NonConstantResourceId")
    private boolean seSelected(int itemId){


        Fragment fragment;
        switch (itemId){
            case R.id.home_navigation :
                fragment = HomeFragment.newInstance() ;
                actionBar.setTitle(R.string.home_fragment_title);
                selectedItem=Constants.HOME_ITEM;
                break;
            case R.id.birds_navigation:
                fragment = BirdsFragment.newInstance(Constants.BIRDS_TAB) ;
                selectedItem=Constants.BIRDS_ITEM;
                actionBar.setTitle(R.string.bird_fragment_title);
                break;
            case R.id.profile_navigation :
                fragment = ProfileFragment.newInstance() ;
                selectedItem=Constants.PROFILE_ITEM;
                actionBar.setTitle(R.string.profile_fragment_title);
                break;
            case R.id.notification_navigation:
                fragment = NotificationFragment.newInstance() ;
                selectedItem=Constants.NOTIFICATION_ITEM;
                actionBar.setTitle(R.string.notification_fragment_title);
                break;
            default:
                return false;
        }
           getSupportFragmentManager().beginTransaction()
              .replace(R.id.main_container, fragment)
               .commit();

        return true ;

    }

    private int getSelectedId() {
        switch (selectedItem){
            case Constants.BIRDS_ITEM :
                return R.id.birds_navigation ;
            case Constants.NOTIFICATION_ITEM :
                return  R.id.notification_navigation ;
            case Constants.PROFILE_ITEM :
                return R.id.profile_navigation ;
            case Constants.HOME_ITEM :
                return  R.id.home_navigation;
        }
        return  R.id.home_navigation;
    }

}