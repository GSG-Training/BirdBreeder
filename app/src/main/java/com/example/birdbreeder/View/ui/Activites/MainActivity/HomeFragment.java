package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.DataBase.Entity.Bird;

import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.View.Adapters.BreederAdapter;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding ;
    private BreederAdapter breederAdapter ;
    private BirdsAdapter birdsAdapter ;
    private BirdViewModel birdViewModel ;
    private Observer<List<Bird>> observer ;
    public HomeFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater , container, false);
        return binding.getRoot() ;
    }
}