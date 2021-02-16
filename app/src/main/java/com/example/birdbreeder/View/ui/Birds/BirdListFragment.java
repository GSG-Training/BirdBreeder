package com.example.birdbreeder.View.ui.Birds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.Constants;
import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.View.ui.Fitter;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.FragmentBirdListBinding;

import java.util.List;

public class BirdListFragment extends Fragment implements BirdsAdapter.OnBirdClickListener {
    public static final String TAG= "com.example.birdbreeder.View.ui.Birds.BirdListFragment" ;
    private BirdsAdapter adapter ;
    private BirdViewModel viewModel ;
    private Observer<List<Bird>> observer ;

    public BirdListFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BirdViewModel(getActivity().getApplication());
        observer = birds -> adapter.setItems(birds);
        adapter = new BirdsAdapter(false , getActivity().getApplication());
        adapter.setOnBirdClickListener(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.example.birdbreeder.databinding.FragmentBirdListBinding birdListBinding = FragmentBirdListBinding.inflate(inflater, container, false);
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);
        birdListBinding.newBirdFb.setOnClickListener(view -> Fitter.toBirdProfile(getActivity().getSupportFragmentManager(), TAG , Constants.NEW_BIRD , -1));
        birdListBinding.birdListRecycler.setAdapter(adapter);
        return birdListBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);
    }


    @Override
    public void itemClick(int position) {
        Bird bird = adapter.getItemAt(position);
        Fitter.toBirdProfile(getActivity().getSupportFragmentManager(), TAG , Constants.SHOW_BIRD , bird.getBirdId());
    }
}