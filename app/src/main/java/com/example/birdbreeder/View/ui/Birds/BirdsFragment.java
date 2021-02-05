package com.example.birdbreeder.View.ui.Birds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.TabsLayoutAdapter;
import com.example.birdbreeder.databinding.FragmentBirdsBinding;


public class BirdsFragment extends Fragment {
    private FragmentBirdsBinding birdsBinding;
    private TabsLayoutAdapter adapter ;
    public BirdsFragment() {
        // Required empty public constructor
    }


    public static BirdsFragment newInstance(String param1, String param2) {
        BirdsFragment fragment = new BirdsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        birdsBinding = FragmentBirdsBinding.inflate(inflater, container, false);
        adapter = new TabsLayoutAdapter(getParentFragmentManager());
        adapter.addFragment(new BirdListFragment() , getString(R.string.bird_fragment_title) , getContext().getDrawable(R.drawable.ic__bird_and_house));
        adapter.addFragment(new MatingsFragment() , getString(R.string.mating_title) , getContext().getDrawable(R.drawable.ic_chicks));
        birdsBinding.mainViewPager.setAdapter(adapter);
        birdsBinding.mainTabs.setupWithViewPager(birdsBinding.mainViewPager);
        adapter.setIcons(birdsBinding.mainTabs);


        return birdsBinding.getRoot();
    }
}