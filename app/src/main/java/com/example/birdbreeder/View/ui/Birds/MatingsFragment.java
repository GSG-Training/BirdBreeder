package com.example.birdbreeder.View.ui.Birds;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.birdbreeder.Model.BirdBreederConstants;
import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.MatingAdapter;
import com.example.birdbreeder.ViewModel.MatingViewModel;
import com.example.birdbreeder.databinding.FragmentMatingsBinding;

import java.util.List;


public class MatingsFragment extends Fragment {
  private FragmentMatingsBinding binding ;
  private MatingViewModel viewModel ;
  private MatingAdapter adapter ;
  private Observer<List<Mating>> observer ;


    public MatingsFragment() {
        // Required empty public constructor
    }


    public static MatingsFragment newInstance(String param1, String param2) {
        MatingsFragment fragment = new MatingsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MatingAdapter();
        viewModel = new MatingViewModel(getActivity().getApplication());
        observer = new Observer<List<Mating>>() {
            @Override
            public void onChanged(List<Mating> matings) {
                adapter.setItems(matings);
            }
        };
    }
   //TODO : OnItemClickListener
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatingsBinding.inflate(inflater , container , false);
        binding.matingListRecycler.setAdapter(adapter);
        viewModel.getAllMatings().observe(getViewLifecycleOwner() , observer);
        binding.newMatingFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , NewMatingActivity.class);
                startActivityForResult(intent , BirdBreederConstants.EDIT_MATING);
            }
        });
        return binding.getRoot();
    }
}