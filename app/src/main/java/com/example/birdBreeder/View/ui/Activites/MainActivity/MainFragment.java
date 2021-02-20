package com.example.birdBreeder.View.ui.Activites.MainActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Adapters.PagerAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.View.ui.Birds.BirdsFragment;
import com.example.birdBreeder.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private PagerAdapter adapter ;
    private int selectedItem ;

    private MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(int selectedItem) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_ITEM , selectedItem);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedItem = getArguments().getInt(Constants.SELECTED_ITEM);
        }
        adapter = new PagerAdapter(requireActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentMainBinding.inflate(inflater, container, false);
        setBottomNavigator();
       // binding.navigator.(getSelectedId());
        return binding.getRoot();
    }




    private void setBottomNavigator(){
        binding.navigator.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_navigation :
                    BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  HomeFragment.newInstance() , " ");
                    selectedItem=Constants.HOME_ITEM;
                    return true ;
                case R.id.birds_navigation:
                    BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  BirdsFragment.newInstance(Constants.BIRDS_TAB) , "");
                    selectedItem=Constants.BIRDS_ITEM;
                    return true ;
                case R.id.profile_navigation :
                    BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  ProfileFragment.newInstance() , "");
                    selectedItem=Constants.PROFILE_ITEM;
                    return true;
                case R.id.notification_navigation:
                    BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  NotificationFragment.newInstance() , "");
                    selectedItem=Constants.NOTIFICATION_ITEM;
                    return true ;
            }
            return false;
        });

        binding.navigator.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_navigation :
                        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  HomeFragment.newInstance() , " ");
                        selectedItem=Constants.HOME_ITEM;
                        return  ;
                    case R.id.birds_navigation:
                        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  BirdsFragment.newInstance(Constants.BIRDS_TAB) , "");
                        selectedItem=Constants.BIRDS_ITEM;
                        return  ;
                    case R.id.profile_navigation :
                        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  ProfileFragment.newInstance() , "");
                        selectedItem=Constants.PROFILE_ITEM;
                        return ;
                    case R.id.notification_navigation:
                        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  NotificationFragment.newInstance() , "");
                        selectedItem=Constants.NOTIFICATION_ITEM;
                        return  ;
                }
            }
        });
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


    private void setPager(){

    }

}