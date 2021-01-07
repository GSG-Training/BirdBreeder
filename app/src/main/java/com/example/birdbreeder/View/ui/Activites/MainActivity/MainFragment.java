package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.TabsLayoutAdapter;
import com.example.birdbreeder.View.ui.Birds.BirdsFragment;
import com.google.android.material.tabs.TabLayout;


public class MainFragment extends Fragment {
    private TabsLayoutAdapter tabAdapter;
    private TabLayout tabs ;
    private ViewPager viewPager ;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabAdapter = new TabsLayoutAdapter(getParentFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tabAdapter = new TabsLayoutAdapter(getParentFragmentManager());
        tabs=view.findViewById(R.id.main_tabs);
        viewPager=view.findViewById(R.id.main_view_pager);
        tabAdapter.addFragment(new HomeFragment() , String.valueOf(R.string.home_fragment_title));
        tabAdapter.addFragment(new ProfileFragment() , String.valueOf(R.string.profile_fragment_title));
        tabAdapter.addFragment(new BirdsFragment() , String.valueOf(R.string.bird_fragment_title));
        tabAdapter.addFragment(new NotificationFragment() , String.valueOf(R.string.notification_fragment_title));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewPager.setAdapter(tabAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}