package com.example.birdBreeder.View.ui.Birds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.View.Adapters.BirdsAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.databinding.FragmentBirdListBinding;

import java.util.List;

public class BirdListFragment extends Fragment implements BirdsAdapter.OnBirdClickListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.BirdListFragment";
    private BirdsAdapter adapter;
    private BirdViewModel viewModel;
    private Observer<List<Bird>> observer;

    private BirdListFragment() {
    }

    public static BirdListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BirdListFragment fragment = new BirdListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BirdViewModel(requireActivity().getApplication());
        observer = birds -> adapter.setItems(birds);
        adapter = new BirdsAdapter(Constants.FOR_SHOW, requireActivity().getApplication());
        adapter.setOnBirdClickListener(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       FragmentBirdListBinding birdListBinding = FragmentBirdListBinding.inflate(inflater, container, false);
        viewModel.getAllBirds().observe(getViewLifecycleOwner(), observer);
        birdListBinding.newBird.setOnClickListener(view -> BrowserHelper.toBirdProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.NEW_BIRD, -1));
        birdListBinding.myBirds.setAdapter(adapter);
        return birdListBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(), observer);
    }


    @Override
    public void itemClick(int position) {
        Bird bird = adapter.getItemAt(position);
        BrowserHelper.toBirdProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.SHOW_BIRD, bird.getBirdId());
    }
}