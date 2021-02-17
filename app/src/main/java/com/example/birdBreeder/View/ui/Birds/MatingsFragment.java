package com.example.birdBreeder.View.ui.Birds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.birdBreeder.Model.Constants;
import com.example.birdBreeder.Model.DataBase.Entity.Mating;
import com.example.birdBreeder.R;
import com.example.birdBreeder.View.Adapters.MatingAdapter;
import com.example.birdBreeder.View.ui.Activites.Helpers.BrowserHelper;
import com.example.birdBreeder.ViewModel.BirdViewModel;
import com.example.birdBreeder.ViewModel.MatingViewModel;
import com.example.birdBreeder.databinding.FragmentMatingsBinding;

import java.util.List;


public class MatingsFragment extends Fragment implements MatingAdapter.OnMatingClickListener {
    public static final String TAG = "com.example.birdBreeder.View.ui.Birds.MatingsFragment";
    private MatingViewModel viewModel;
    private MatingAdapter adapter;
    private Observer<List<Mating>> observer;
    private BirdViewModel birdViewModel ;
    private Observer<List<String>> listObserver ;
    private boolean checked = false ;

    public MatingsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MatingAdapter();
        adapter.setOnItemClickListener(this);
        viewModel = new MatingViewModel(requireActivity().getApplication());
        birdViewModel = new BirdViewModel(requireActivity().getApplication());
        setObservers();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.example.birdBreeder.databinding.FragmentMatingsBinding binding = FragmentMatingsBinding.inflate(inflater, container, false);
        binding.matingListRecycler.setAdapter(adapter);
        viewModel.getAllMatings().observe(getViewLifecycleOwner(), observer);
        binding.newMatingFb.setOnClickListener(view -> birdViewModel.getRingNOfFemales().observe(getViewLifecycleOwner(), listObserver ));
        return binding.getRoot();
    }


    private void setObservers(){
        observer = matings -> adapter.setItems(matings);
        listObserver = strings -> {
            if(strings.isEmpty()){
                Toast.makeText(getContext(), R.string.check_list, Toast.LENGTH_SHORT).show();
            } else if(!checked){
                checked = true ;
                birdViewModel.getRingNOfMales().observe(getViewLifecycleOwner() , listObserver);
            }else {
                BrowserHelper.toMatingProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.NEW_MATING, -1);
            }

        };
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllMatings().observe(getViewLifecycleOwner() , observer);
    }

    @Override
    public void onMatingClick(int position) {
        Mating mating = adapter.getItemAt(position);
        BrowserHelper.toFragment(requireActivity().getSupportFragmentManager() ,  MatingControlFragment.newInstance(mating.getMatingId()) , TAG);

    }

    @Override
    public void onEditClick(int position) {
        Mating mating = adapter.getItemAt(position);
        BrowserHelper.toMatingProfile(requireActivity().getSupportFragmentManager(), TAG, Constants.EDIT_MATING, mating.getMatingId());
    }
}