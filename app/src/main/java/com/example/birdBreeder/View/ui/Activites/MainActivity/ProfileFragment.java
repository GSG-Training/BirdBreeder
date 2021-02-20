package com.example.birdBreeder.View.ui.Activites.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Bird;
import com.example.birdBreeder.Model.DataBase.Entity.Species;
import com.example.birdBreeder.View.Adapters.BirdsAdapter;
import com.example.birdBreeder.View.Adapters.SpeciesAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.ViewModel.SpeciesViewModel;
import com.example.birdBreeder.databinding.FragmentProfileBinding;

import java.util.List;


public class ProfileFragment extends Fragment implements View.OnClickListener, BirdsAdapter.OnBirdClickListener {
    public static final String TAG = "com.example.birdbreeder.View.ui.Activites.MainActivity.ProfileFragment";
    private FragmentProfileBinding binding;
    private SpeciesAdapter speciesAdapter;
    private BirdsAdapter birdsAdapter;
    private BirdViewModel birdViewModel;
    private SpeciesViewModel speciesViewModel;
    private Observer<List<Species>> speciesObserver;
    private Observer<List<Bird>> birdsObserver;

    private ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speciesViewModel = new SpeciesViewModel(getActivity().getApplication());
//        speciesViewModel.deleteAll();
//         Dummy.addSpecies(getActivity().getApplication());
        birdViewModel = new BirdViewModel(getActivity().getApplication());
        speciesAdapter = new SpeciesAdapter();
        birdsAdapter = new BirdsAdapter(Constants.FOR_SALE, getActivity().getApplication());
        birdsAdapter.setOnBirdClickListener(this);
        speciesObserver = new Observer<List<Species>>() {
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.birdsRecycler.setAdapter(birdsAdapter);
        binding.speciesRecycler.setAdapter(speciesAdapter);
        //TODO: get the right items "FOR SALE , OWNED SPECIES"
        birdViewModel.getAllBirds().observe(getViewLifecycleOwner(), birdsObserver);
        speciesViewModel.getAllSpecies().observe(getViewLifecycleOwner(), speciesObserver);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        //TODO: EDIT , FAV
    }

    @Override
    public void itemClick(int position) {
        Bird bird = birdsAdapter.getItemAt(position);
        BrowserHelper.toBirdProfile(getActivity().getSupportFragmentManager(), TAG, Constants.SHOW_BIRD, bird.getBirdId());
    }
}