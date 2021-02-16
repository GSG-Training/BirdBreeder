package com.example.birdbreeder.View.ui.Birds;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.birdbreeder.Model.Constants;
import com.example.birdbreeder.Model.DataBase.Entity.Mating;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.MatingAdapter;
import com.example.birdbreeder.View.ui.Fitter;
import com.example.birdbreeder.ViewModel.MatingViewModel;
import com.example.birdbreeder.databinding.FragmentMatingsBinding;

import java.util.List;


public class MatingsFragment extends Fragment implements MatingAdapter.OnMatingClickListener {
  public static final String TAG= "com.example.birdbreeder.View.ui.Birds.MatingsFragment" ;
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
        adapter.setOnItemClickListener(this);
        viewModel = new MatingViewModel(getActivity().getApplication());
        observer = matings -> adapter.setItems(matings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatingsBinding.inflate(inflater , container , false);
        binding.matingListRecycler.setAdapter(adapter);
        viewModel.getAllMatings().observe(getViewLifecycleOwner() , observer);
        binding.newMatingFb.setOnClickListener(view -> {
            Fitter.toMatingProfile( getActivity().getSupportFragmentManager(),TAG ,Constants.NEW_MATING , -1);
        });
        return binding.getRoot();
    }

    @Override
    public void onMatingClick() {

    }
}