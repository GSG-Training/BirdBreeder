package com.example.birdBreeder.View.ui.Birds;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Adapters.PagerAdapter;
import com.example.birdBreeder.databinding.FragmentBirdsBinding;


public class BirdsFragment extends Fragment {
    public static final String TAG = "com.example.birdbreeder.View.ui.Birds.BirdsFragment";
    private FragmentBirdsBinding birdsBinding;
    private PagerAdapter adapter;
    int selectedTab ;

    private BirdsFragment() {
        // Required empty public constructor
    }


    public static BirdsFragment newInstance(int selectedTab) {
        BirdsFragment fragment = new BirdsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.SELECTED_TAB , selectedTab);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedTab = getArguments().getInt(Constants.SELECTED_TAB);
        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        birdsBinding = FragmentBirdsBinding.inflate(inflater, container, false);
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment( BirdListFragment.newInstance() , getString(R.string.bird_fragment_title), requireContext().getDrawable(R.drawable.ic__bird_and_house));
        adapter.addFragment( MatingsFragment.newInstance(), getString(R.string.mating_title), requireContext().getDrawable(R.drawable.ic_chicks));
        birdsBinding.mainViewPager.setAdapter(adapter);
        birdsBinding.mainTabs.setupWithViewPager(birdsBinding.mainViewPager);
        birdsBinding.mainViewPager.setCurrentItem(selectedTab,true);
        adapter.setIcons(birdsBinding.mainTabs);
        birdsBinding.mainViewPager.setCurrentItem(selectedTab);
        return birdsBinding.getRoot();
    }
}