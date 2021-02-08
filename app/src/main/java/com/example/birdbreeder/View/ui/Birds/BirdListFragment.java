package com.example.birdbreeder.View.ui.Birds;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.databinding.FragmentBirdListBinding;

import java.util.List;

public class BirdListFragment extends Fragment {
  private FragmentBirdListBinding birdListBinding ;
  private BirdsAdapter adapter ;
  private BirdViewModel viewModel ;
  private Observer<List<Bird>> observer ;

    public BirdListFragment() {
    }


    public static BirdListFragment newInstance(String param1, String param2) {
        BirdListFragment fragment = new BirdListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BirdViewModel(getActivity().getApplication());
        observer = new Observer<List<Bird>>() {
            @Override
            public void onChanged(List<Bird> birds) {
                adapter.setItems(birds);
            }
        };
    }
   //TODO: onItemClick Listener

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        birdListBinding = FragmentBirdListBinding.inflate(inflater , container , false);
        adapter = new BirdsAdapter(false);
        birdListBinding.birdListRecycler.setAdapter(adapter);
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);

        return birdListBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllBirds().observe(getViewLifecycleOwner(),observer);
    }
}