package com.example.birdBreeder.View.ui.Activites.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.Model.DataBase.Entity.Breeder;
import com.example.birdBreeder.View.Adapters.BirdsAdapter;
import com.example.birdBreeder.View.Adapters.BreederAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements BirdsAdapter.OnBirdClickListener {
    public static final String TAG = "com.example.birdbreeder.View.ui.Activites.MainActivity.HomeFragment";
    private BreederAdapter breederAdapter;
    private BirdsAdapter birdsAdapter;
    private BirdViewModel birdViewModel;
    private Observer<List<Bird>> observer;
    private List<Breeder> breeders;

    public HomeFragment() {
    }

    //TODO: CONNECT TO THE FIREBASE
    //TODO : CHECK INTERNET


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummy();
        birdsAdapter = new BirdsAdapter(true, getActivity().getApplication());
        birdsAdapter.setOnBirdClickListener(this);
        breederAdapter = new BreederAdapter();
        breederAdapter.setItems(breeders);
        birdViewModel = new BirdViewModel(getActivity().getApplication());
        observer = birds -> birdsAdapter.setItems(birds);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.example.birdBreeder.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.birdRecycler.setAdapter(birdsAdapter);
        binding.breederRecycler.setAdapter(breederAdapter);
        birdViewModel.getAllBirds().observe(getViewLifecycleOwner(), observer);
        return binding.getRoot();
    }

    private void dummy() {
        breeders = new ArrayList<>();
        Breeder first = new Breeder(1, "First Breeder", "first@email.com");
        first.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        first.setSpeciesNum(23);
        first.setSoldNum(20);
        first.setFav(true);
        Breeder second = new Breeder(2, "Second Breeder", "second@email.com");
        second.setDescription("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        second.setSpeciesNum(40);
        second.setSoldNum(25);
        Breeder third = new Breeder(3, "Third Breeder", "third@email.com");
        third.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        third.setSpeciesNum(50);
        third.setSoldNum(45);
        Breeder fourth = new Breeder(4, "Fourth Breeder", "fourth@email.com");
        fourth.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        fourth.setSpeciesNum(23);
        fourth.setSoldNum(20);
        breeders.add(first);
        breeders.add(second);
        breeders.add(third);
        breeders.add(fourth);

    }

    @Override
    public void itemClick(int position) {
        Bird bird = birdsAdapter.getItemAt(position);
        BrowserHelper.toBirdProfile(getActivity().getSupportFragmentManager(), TAG, Constants.SHOW_BIRD, bird.getBirdId());
    }
}