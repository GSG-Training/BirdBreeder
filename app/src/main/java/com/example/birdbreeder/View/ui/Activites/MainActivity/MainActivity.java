package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintSet.Layout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.TabsLayoutAdapter;
import com.example.birdbreeder.View.ui.Birds.BirdsFragment;
import com.example.birdbreeder.View.ui.Birds.MatingProfileFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabsLayoutAdapter tabAdapter;
    private TabLayout tabs ;
    private ViewPager viewPager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        MainFragment mainFragment = new MainFragment();
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, mainFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
//        tabAdapter = new TabsLayoutAdapter(getSupportFragmentManager());
//        tabs=findViewById(R.id.main_tabs);
//        viewPager=findViewById(R.id.main_view_pager);
//        tabAdapter.addFragment(new HomeFragment() , getString(R.string.home_fragment_title), getDrawable(R.drawable.ic_baseline_home_24));
//        tabAdapter.addFragment(new ProfileFragment() , getString(R.string.profile_fragment_title), getDrawable(R.drawable.ic_baseline_person_24));
//        tabAdapter.addFragment(new BirdsFragment() , getString(R.string.bird_fragment_title), getDrawable(R.drawable.ic__bird));
//        tabAdapter.addFragment(new NotificationFragment() , getString(R.string.notification_fragment_title), getDrawable(R.drawable.ic_baseline_notifications_active_24));
//        viewPager.setAdapter(tabAdapter);
//        tabs.setupWithViewPager(viewPager);
//        tabAdapter.setIcons(tabs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }


    //TODO: activate Search ;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}