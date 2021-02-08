package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.DataBase.Entity.Bird;

import com.example.birdbreeder.Model.DataBase.Entity.Breeder;
import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.View.Adapters.BreederAdapter;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding ;
    private BreederAdapter breederAdapter ;
    private BirdsAdapter birdsAdapter ;
    private BirdViewModel birdViewModel ;
    private Observer<List<Bird>> observer ;
    private List<Breeder> breeders ;
    public HomeFragment() {
    }

    //TODO: CONNECT TO THE FIREBASE
    //TODO : CHECK INTERNET


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummy();
        birdsAdapter = new BirdsAdapter(true);
        breederAdapter = new BreederAdapter();
        breederAdapter.setItems(breeders);
        birdViewModel = new BirdViewModel(getActivity().getApplication());
        observer = new Observer<List<Bird>>() {
            @Override
            public void onChanged(List<Bird> birds) {
               birdsAdapter.setItems(birds);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater , container, false);
        binding.birdRecycler.setAdapter(birdsAdapter);
        binding.breederRecycler.setAdapter(breederAdapter);
        birdViewModel.getAllBirds().observe(getViewLifecycleOwner() , observer);
        return binding.getRoot() ;
    }

    private void dummy(){
        breeders = new ArrayList<>();
        Breeder first = new Breeder(1 , "First Breeder" , "first@email.com");
        first.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        first.setSpeciesNum(23);
        first.setSoldNum(20);
        first.setFav(true);
        Breeder second = new Breeder(2 , "Second Breeder" , "second@email.com");
        second.setDescription("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        second.setSpeciesNum(40);
        second.setSoldNum(25);
        Breeder third = new Breeder(3 , "Third Breeder" , "third@email.com");
        third.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        third.setSpeciesNum(50);
        third.setSoldNum(45);
        Breeder fourth = new Breeder(4 , "Fourth Breeder" , "fourth@email.com");
        fourth.setDescription("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        fourth.setSpeciesNum(23);
        fourth.setSoldNum(20);
        breeders.add(first);
        breeders.add(second);
        breeders.add(third);
        breeders.add(fourth);

    }
}