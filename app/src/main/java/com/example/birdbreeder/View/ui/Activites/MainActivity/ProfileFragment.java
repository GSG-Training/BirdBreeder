package com.example.birdbreeder.View.ui.Activites.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdbreeder.Model.DataBase.Entity.Bird;
import com.example.birdbreeder.Model.DataBase.Entity.Species;
import com.example.birdbreeder.R;
import com.example.birdbreeder.View.Adapters.BirdsAdapter;
import com.example.birdbreeder.View.Adapters.SpeciesAdapter;
import com.example.birdbreeder.View.ui.Dummy;
import com.example.birdbreeder.ViewModel.BirdViewModel;
import com.example.birdbreeder.ViewModel.SpeciesViewModel;
import com.example.birdbreeder.databinding.FragmentProfileBinding;

import java.util.List;


public class ProfileFragment extends Fragment implements View.OnClickListener {
private FragmentProfileBinding binding ;
private SpeciesAdapter speciesAdapter ;
private BirdsAdapter birdsAdapter ;
private BirdViewModel birdViewModel ;
private SpeciesViewModel speciesViewModel ;
private Observer<List<Species>> speciesObserver ;
private Observer<List<Bird>> birdsObserver ;
    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Dummy.addBirds(getActivity().getApplication());
     //   Dummy.addSpecies(getActivity().getApplication());
        speciesViewModel = new SpeciesViewModel(getActivity().getApplication());
        birdViewModel = new BirdViewModel(getActivity().getApplication());
        speciesAdapter = new SpeciesAdapter();
        birdsAdapter = new BirdsAdapter(true);
        speciesObserver= new Observer<List<Species>>() {
            @Override
            public void onChanged(List<Species> species) {
                speciesAdapter.setItems(species);
            }
        };

        birdsObserver = new Observer<List<Bird>>() {
            @Override
            public void onChanged(List<Bird> birds) {
                birdsAdapter.setItems(birds);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater , container , false);
        binding.birdsRecycler.setAdapter(birdsAdapter);
        binding.speciesRecycler.setAdapter(speciesAdapter);
        //TODO: get the right items "FOR SALE , OWNED SPECIES"
        birdViewModel.getAllBirds().observe(getViewLifecycleOwner(),birdsObserver);
        speciesViewModel.getAllSpecies().observe(getViewLifecycleOwner(),speciesObserver);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        //TODO: EDIT , FAV
    }

}